package ls.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ls.commands.CommandsUtils;
import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;

public class CRUD {
	private static DataBaseManager link;
	static ResultSet rs;
	static Statement stmt;
	private static PreparedStatement prep;
	
	
	public static ArrayList<String> executeQuery(String cmdSel) throws  ConnectionDatabaseException, IllegalCommandException, FileException
	{
		try{
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery(cmdSel);
			ArrayList<String> select = new ArrayList<>();
			select = CommandsUtils.resultSetToArrayList(rs);
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

	public static int executeNonQuery(String cmd) throws ConnectionDatabaseException, FileException
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
	
	public static ArrayList<String> executeQuery(String cmd, String [] params) throws IllegalCommandException, ConnectionDatabaseException, FileException{
		try{
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement(cmd);
			for(int i = 1; i<=params.length;i++)
			{
				prep.setString(i, params[i-1]);
			}
			rs = prep.executeQuery();
			ArrayList<String> select = new ArrayList<>();
			select = CommandsUtils.resultSetToArrayList(rs);
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
			if(prep != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new ConnectionDatabaseException("Statement could not be closed");
				}
			if(link != null)
				link.closeConnection();
		}
	}
	
	public static int executeNonQuery(String cmd, String [] params) throws ConnectionDatabaseException, FileException{
		int rows = 0;
		link = new DataBaseManager();
		try {
			prep = link.getConnetion().prepareStatement(cmd);
			for(int i = 1; i<=params.length;i++)
			{
				prep.setString(i, params[i-1]);
			}
			rows = prep.executeUpdate();
			return rows;
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		}
		finally{
			if(prep != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new ConnectionDatabaseException("Statement could not be closed");
				}
			if(link != null)
				link.closeConnection();
		}
	}



}
