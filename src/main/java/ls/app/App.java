package ls.app;


import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.*;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.propertiesRental.Rental;
import ls.utils.Utils;

public class App {
	
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users/joao

	public static void main(String[] args) throws IllegalCommandException, ConnectionDatabaseException 
	{		
		Rental gest = new Rental();
		gest.add("GET /users", new GetUsers());
		gest.add("GET /users/{username}", new GetUserUsername());
		gest.add("GET /properties", new GetProperties());
		gest.add("GET /properties/details/{pid}", new GetPropertiesDetails());
		gest.add("GET /properties/location/{location}", new GetPropertiesLocation());
		gest.add("GET /properties/owner/{owner}", new GetPropertiesOwner());
		gest.add("GET /properties/type/{type}", new GetPropertiesType());
		gest.add("POST /users", new PostUsers());
		gest.add("POST /properties", new PostProperties());
		
		HashMap <String,String> map = new HashMap<String, String>(); 

		ICommand cmd = gest.find(args,map);
		ArrayList<String> result = cmd.execute(map);
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


