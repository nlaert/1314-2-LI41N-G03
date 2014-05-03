package ls.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;

public abstract class CommandsUtils {

	
	public void close(Statement stmt, DataBaseManager link) throws ConnectionDatabaseException
	{
		close(null,stmt,link);
	}
	
	
	public void close(ResultSet rs, Statement stmt, DataBaseManager link) throws ConnectionDatabaseException
	{
		if(rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				throw new ConnectionDatabaseException("ResultSet could not be closed");
			}
		if(stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new ConnectionDatabaseException("Statement could not be closed");
			}
		if(link != null)
			link.closeConnection();
	}
	
	public static boolean checkAuth(String username, String password, Connection conn) throws SQLException
	{
		String auth = ("Select username from Users Where username = ? and password = ?");
		return checkIfExists(auth, new String [] {username, password}, conn);
	}
	
	public static boolean checkIfExists(String sqlCommand,String[] option, Connection conn) throws SQLException
	{
		PreparedStatement prep = conn.prepareStatement(sqlCommand);
		for(int i = 1; i<=option.length;i++)
		{
			prep.setString(i, option[i-1]);
		}
		
		return prep.executeQuery().next();
	}
	
	public static ArrayList<String> resultSetToArrayList(ResultSet rs) throws IllegalCommandException, SQLException
	{
		if(!rs.next())
			throw new IllegalCommandException("Parameters not found");
		int columnCount = rs.getMetaData().getColumnCount();
		ArrayList<String> select = new ArrayList<String>(columnCount+1);
		StringBuilder aux = new StringBuilder();
		for (int i = 1; i<=columnCount; i++)
			aux.append(rs.getMetaData().getColumnName(i) + "\t");
		select.add(aux.toString());


		do{
			aux = new StringBuilder();
			for (int i = 1; i<=columnCount; i++)
				aux.append(rs.getString(i) + "\t");
			select.add(aux.toString());
		} while(rs.next());

		return select;
	}

	
}
