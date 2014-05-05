package ls.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ls.exception.ConnectionDatabaseException;

public class CRUD {
	private static DataBaseManager link;
	static ResultSet rs;
	static Statement stmt;
	
	
	public static ArrayList<String> executeQuery(String cmdSel) throws  ConnectionDatabaseException
	{
		try{
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
			return select;
		}catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		}

		finally{
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
		

	}

	public static int executeNonQuery(String cmd) throws ConnectionDatabaseException
	{
		int rows = 0;
		link = new DataBaseManager();
		try {
			stmt = link.getConnetion().createStatement();
			rows = stmt.executeUpdate(cmd);
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		}
		finally{
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

		return rows;
	}



}
