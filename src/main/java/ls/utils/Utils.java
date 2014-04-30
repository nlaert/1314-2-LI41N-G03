package ls.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;

public class Utils {
	
	/**
	 * 
	 * @param command
	 * @return String[] with 2 positions, the first and second part of String command divided by "/"
	 * @throws IllegalCommandException 
	 */
	Statement stmt;
	ResultSet rs;
	DataBaseManager link;
	

	
	public static String removeParameterList(String command) throws IllegalCommandException 
	{
		if(command == null || command.equals(""))
			throw new IllegalCommandException("Invalid command");
		char limitator = ' ';
		int firstIndex = command.indexOf(limitator);
		int lastIndex = command.lastIndexOf(limitator);
		if(firstIndex == lastIndex)
			return command;
		else
		{
			return command.substring(0,lastIndex);
		}
	}
	
	public static String limitatorSpecificCommand(String command) throws IllegalCommandException
	{
		if (command.contains("location"))
			command = locationTransformer(command);
		int lastIndex = command.lastIndexOf("/");
		return command.substring(lastIndex+1);
	}
	
	public static boolean checkAuth(String username, String password, Connection conn) throws SQLException
	{
		PreparedStatement prep = conn.prepareStatement("Select username from Users Where username = ? and password = ?");
		prep.setString(1, username);
		prep.setString(2, password);
		return prep.executeQuery().next();
	}
	
	public static boolean checkRental(String sqlCommand,String year, String cw, Connection conn) throws SQLException
	{
		PreparedStatement prep = conn.prepareStatement(sqlCommand);
		prep.setString(1, year);
		prep.setString(2, cw);
		return prep.executeQuery().next();
	}
	
	public static String locationTransformer(String command) throws IllegalCommandException{
		if (command == null || command == "" || command.contains(", "))
			throw new IllegalCommandException("invalid command");
		return command.replace("|", ", ");
	}
	
	public static HashMap<String, String> mapper(String parameters, HashMap<String, String> map) throws IllegalCommandException
	{
		if (parameters == null)
			throw new IllegalCommandException("No parameter list found");
		if (parameters.equals(""))
			throw new IllegalCommandException("No parameter list found");
		if (parameters.contains("location"))
			parameters = locationTransformer(parameters);
		String [] aux = parameters.split("&");
		int equal = 0;
		for (int i = 0; i < aux.length; i++)
		{
			if (aux[i].indexOf('+')>=0)
				aux[i] = aux[i].replace('+', ' ');
			equal = aux[i].indexOf('=');
			map.put(aux[i].substring(0, equal), aux[i].substring(equal+1));			
		}
		return map;
	}
	
	public static ArrayList<String> resultSetToArrayList(ResultSet rs) throws SQLException 
	{
		int columnCount = rs.getMetaData().getColumnCount();
		ArrayList<String> select = new ArrayList<String>(columnCount+1);
		StringBuilder aux = new StringBuilder();
		for (int i = 1; i<=columnCount; i++)
			aux.append(rs.getMetaData().getColumnName(i) + "\t");
		select.add(aux.toString());
		while(rs.next())
		{
			aux = new StringBuilder();
			for (int i = 1; i<=columnCount; i++)
				aux.append(rs.getString(i) + "\t");
			select.add(aux.toString());
		}
		return select;
	}

	public static String[] pathParameters(String path, String command) throws IllegalCommandException {
		ArrayList <String> list = new ArrayList<String>();
		command = Utils.removeParameterList(command);
		String [] arraySplitPath = path.split("/");
		String [] arraySplitCommand = command.split("/");
		for(int i = 0; i<arraySplitPath.length; i++)
		{
			if(arraySplitPath[i].contains("{"))
				list.add(arraySplitCommand[i]);
		}
		return  list.toArray(new String[] {});
	}
	

	
}
