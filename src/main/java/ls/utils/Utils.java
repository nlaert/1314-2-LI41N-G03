package ls.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.IllegalCommandException;

public class Utils {
	
	/**
	 * 
	 * @param command
	 * @return String[] with 2 positions, the first and second part of String command divided by "/"
	 * @throws IllegalCommandException 
	 */

	
	public static String limitatorPath(String command) throws IllegalCommandException 
	{
		char limitator;
		if(command.contains("GET "))
		{
			limitator = '/';
		}
		else if(command.contains("POST "))
		{
			limitator = ' ';
		}
		else
			throw new IllegalCommandException("Comando invalido!");
		
		int firstIndex = command.indexOf(limitator);
		int lastIndex = command.lastIndexOf(limitator);
		if(firstIndex == lastIndex)
			return command;
		else
		{
			if(limitator == '/')
				return command.substring(0,lastIndex+1);
			else
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
	
	public static String locationTransformer(String command) throws IllegalCommandException{
		if (command == null || command == "" || command.contains(", "))
			throw new IllegalCommandException("invalid command");
		return command.replace("|", ", ");
	}
	
	public static HashMap<String, String> mapper(String parameters) throws IllegalCommandException
	{
		if (parameters == null)
			throw new IllegalCommandException("No parameter list found");
		if (parameters.contains(" "))
			parameters = parameters.substring(parameters.lastIndexOf(' ')+1);
		if (parameters.equals(""))
			throw new IllegalCommandException("No parameter list found");
		HashMap <String, String> dict = new HashMap<String, String>();
		if (parameters.contains("location"))
			parameters = locationTransformer(parameters);
		String [] aux = parameters.split("&");
		int equal = 0;
		for (int i = 0; i < aux.length; i++)
		{
			if (aux[i].indexOf('+')>=0)
				aux[i] = aux[i].replace('+', ' ');
			equal = aux[i].indexOf('=');
			dict.put(aux[i].substring(0, equal), aux[i].substring(equal+1));			
		}
		return dict;
	}
	
	public static ArrayList<String> resultSetToArrayList(ResultSet rs) throws SQLException
	{
		int columnCount = rs.getMetaData().getColumnCount();
		ArrayList<String> select = new ArrayList<String>(columnCount);
		while(rs.next())
		{
			StringBuilder aux = new StringBuilder();
			for (int i = 1; i<=columnCount; i++)
				aux.append(rs.getString(i) + "\t");
			select.add(aux.toString());
		}
		return select;
	}
	
	public static String argsToString(String [] args) throws IllegalCommandException{
		if (args == null || args.length<1)
			throw new IllegalCommandException("invalid command");
		StringBuilder str = new StringBuilder();
		int i;
		for (i = 0; i < args.length-1; i++){
			str.append(args[i] + " ");
		}
		str.append(args[i]);
		return str.toString();
	}
	
	public static <E> void printArrayList(ArrayList<E> list){
		for (int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).toString());
		}
		System.out.println();
	}
}
