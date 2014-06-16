package ls.utils;


import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.IllegalCommandException;

public class Utils {
	
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
		if (map == null)
			throw new IllegalCommandException("Invalid HashMap");
		if (parameters.contains("location"))
			parameters = locationTransformer(parameters);
		String [] aux = parameters.split("&");
		int equal = 0;
		for (int i = 0; i < aux.length; i++)
		{
			if (aux[i].indexOf('+')>=0)
				aux[i] = aux[i].replace('+', ' ');
			equal = aux[i].indexOf('=');
			if (equal<0)
				throw new IllegalCommandException("Invalid Parameter List");
			map.put(aux[i].substring(0, equal), aux[i].substring(equal+1));			
		}
		return map;
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
