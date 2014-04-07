package ls.jdbc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.utils.Utils;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DataBaseManager {

	private static Connection connection;
	private static SQLServerDataSource ds = new SQLServerDataSource();
	
	public DataBaseManager() 
	{
		
		try{
			readConnectionFile();
//			ds.setServerName("10.211.55.9");
			connection = ds.getConnection();
		}
		catch(SQLException e)
		{
			System.out.println("Nao e possivel abrir uma nova ligacao.");
		} catch (IOException e) {
			System.out.println("Connection file error");
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
	
	private void readConnectionFile() throws IOException{
		InputStream istream = this.getClass().getResourceAsStream("config.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(istream));
		HashMap<String, String> config;
		try {
			config = Utils.mapper(br.readLine());
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