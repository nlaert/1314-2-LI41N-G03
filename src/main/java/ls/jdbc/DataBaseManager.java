package ls.jdbc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;
import ls.utils.Utils;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DataBaseManager {

	private static Connection connection;
	private static SQLServerDataSource ds = new SQLServerDataSource();
	
	public DataBaseManager() throws FileException, ConnectionDatabaseException 
	{
		
		try{
			readConnectionFile();
//			ds.setServerName("10.211.55.9");
			connection = ds.getConnection();
		}
		catch(SQLException e)
		{
			throw new ConnectionDatabaseException("Connection error");
		} catch (IOException e) {
			throw new FileException("config.txt file not found");
		}

	}

	public void closeConnection() throws ConnectionDatabaseException   {
		try {
			connection.close();
		} catch (SQLException  | NullPointerException e) {
			throw new ConnectionDatabaseException("Connection error");
		}

	}

	public Connection getConnetion() {
		return connection;
	}
	
	private void readConnectionFile() throws IOException{
		InputStream istream = this.getClass().getResourceAsStream("config.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(istream));
		HashMap<String, String> config = new HashMap<String,String>();
		try {
			config = Utils.mapper(br.readLine(),config);
		} catch (IllegalCommandException e) {
			throw new IOException("invalid connection file");
		}
		finally{
			br.close();
		}
		ds.setServerName(config.get("serverName"));
		ds.setPortNumber(Integer.parseInt(config.get("portNumber")));
		ds.setDatabaseName(config.get("databaseName"));
		ds.setUser(config.get("user"));
		ds.setPassword(config.get("password"));
	}

}