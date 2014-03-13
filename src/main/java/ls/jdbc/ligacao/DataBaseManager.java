package ls.jdbc.ligacao;
import java.sql.*;

public class DataBaseManager {

	//private static final String url = "jdbc:sqlserver://localhost\\SI1;integratedSecurity=true;databaseName=KYIS";
	private static final String url = "jdbc:sqlserver://10.211.55.8:1433;user=ls;password=ls1314;databaseName=LS";
	private static Connection connection;
	
	
	public DataBaseManager() 
	{
		try{
			connection = DriverManager.getConnection(url);
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