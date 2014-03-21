package ls.jdbc;
import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DataBaseManager {

	private static Connection connection;
	private static SQLServerDataSource ds = new SQLServerDataSource();
	
	public DataBaseManager() 
	{
		ds.setServerName("10.211.55.9");
	//	ds.setServerName("localhost");
		ds.setPortNumber(1433);
		ds.setDatabaseName("LS");
		ds.setUser("ls");
		ds.setPassword("ls1314");
		
		try{
			connection = ds.getConnection();
		}
		catch(SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}

	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Connection getConnetion() {
		return connection;
	}

}