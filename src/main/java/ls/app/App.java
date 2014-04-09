package ls.app;


import java.util.ArrayList;

import ls.commands.*;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.propertiesRental.Commands;
import ls.utils.Utils;

public class App {
	
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users/joao

	public static void main(String[] args) throws IllegalCommandException, ConnectionDatabaseException 
	{		
		Commands gest = new Commands();
		gest.add("GET /users", new GetUsers());
		gest.add("GET /users/", new GetUserUsername());
		gest.add("GET /properties", new GetProperties());
		gest.add("GET /properties/details/", new GetPropertiesDetails());
		gest.add("GET /properties/location/", new GetPropertiesLocation());
		gest.add("GET /properties/owner/", new GetPropertiesOwner());
		gest.add("GET /properties/type/", new GetPropertiesType());
		gest.add("POST /users", new PostUsers());
		gest.add("POST /properties", new PostProperties());
		
		String command = argsToString(args);
		iCommand cmd = gest.find(command);
		ArrayList<String> result = cmd.execute(command);
		printArrayList(result);
		
		

		
		
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

