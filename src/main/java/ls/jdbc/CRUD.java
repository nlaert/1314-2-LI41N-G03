package ls.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Exception.ConnectionDatabaseException;

public class CRUD {
	private static DataBaseManager link;
	static ResultSet rs;
	static Statement stmt;
	
	
	public static ArrayList<String> executeQuery(String cmdSel) throws SQLException, ConnectionDatabaseException
	{
		link = new DataBaseManager();
		stmt = link.getConnetion().createStatement();
		rs = stmt.executeQuery(cmdSel);
		ArrayList<String> select = new ArrayList<>();
		int columnCount = rs.getMetaData().getColumnCount();
		while(rs.next())
		{
			StringBuilder aux = new StringBuilder();
			for (int i = 1; i<=columnCount; i++)
				aux.append(rs.getString(i) + "\t");
			select.add(aux.toString());
		}
		
		if(rs != null)
			rs.close();
		if(stmt != null)
			stmt.close();
		if(link != null)
			link.closeConnection();
		return select;
		
	}

	public static int executeNonQuery(String cmd) throws SQLException, ConnectionDatabaseException
	{
		int rows = 0;
		link = new DataBaseManager();
		stmt = link.getConnetion().createStatement();
		rows = stmt.executeUpdate(cmd);

		if(rs != null)
			rs.close();
		if(stmt != null)
			stmt.close();
		if(link != null)
			link.closeConnection();

		return rows;
	}
	
	
	
}
