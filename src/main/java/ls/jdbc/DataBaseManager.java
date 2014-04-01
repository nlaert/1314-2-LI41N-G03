package ls.jdbc;
import java.sql.*;
import Exception.ConnectionDatabaseException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DataBaseManager {

	private static Connection connection;
	private static SQLServerDataSource ds = new SQLServerDataSource();
	
	public DataBaseManager() 
	{
//		ds.setServerName("10.211.55.9");
		ds.setServerName("localhost");
		ds.setPortNumber(1433);
		ds.setDatabaseName("LS");
		ds.setUser("ls");
		ds.setPassword("ls1314");
		
		try{
			connection = ds.getConnection();
		}
		catch(SQLException e)
		{
			System.out.println("Nao e possivel abrir uma nova ligacao.");
		}

	}

	public void closeConnection() throws ConnectionDatabaseException   {
		try {
			connection.close();
		} catch (SQLException  | NullPointerException e) {
			throw new ConnectionDatabaseException("Erro ao fechar a Ligacao",e);
		}

	}

	public Connection getConnetion() {
		return connection;
	}

}