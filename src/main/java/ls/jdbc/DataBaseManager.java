package ls.jdbc;
import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DataBaseManager {

//	private static final String url = "jdbc:sqlserver://10.211.55.8:1433;user=ls;password=ls1314;databaseName=LS";
	private static Connection connection;
	private static SQLServerDataSource ds = new SQLServerDataSource();
	
	public DataBaseManager() 
	{
		ds.setServerName("10.211.55.8");
//		ds.setServerName("localhost");
		ds.setPortNumber(1433);
		ds.setDatabaseName("LS");
		ds.setUser("ls");
		ds.setPassword("ls1314");
		
		try{
			connection = ds.getConnection();
//			connection = DriverManager.getConnection(url);
		}
		catch(SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}

	}

	public void closeConnection() throws SQLException {
		connection.close();
	}

	public Connection getConnetion() {
		return connection;
	}

}