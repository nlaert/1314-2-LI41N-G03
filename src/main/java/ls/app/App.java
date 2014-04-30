package ls.app;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.*;
import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;
import ls.output.Output;
import ls.propertiesRental.Rental;

public class App {
	
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users/joao

	public static void main(String[] args) throws IllegalCommandException, ConnectionDatabaseException, IOException, FileException 
	{		
		Rental gest = new Rental();
		gest.add("GET /users", new GetUsers());
		gest.add("GET /users/{username}", new GetUserUsername());
		gest.add("GET /properties", new GetProperties());
		gest.add("GET /properties/details/{pid}", new GetPropertiesDetails());
		gest.add("GET /properties/location/{location}", new GetPropertiesLocation());
		gest.add("GET /properties/owner/{owner}", new GetPropertiesOwner());
		gest.add("GET /properties/type/{type}", new GetPropertiesType());
		gest.add("GET /users/{username}/rentals", new GetUsersRentals());
		gest.add("POST /users", new PostUsers());
		gest.add("POST /properties", new PostProperties());
		gest.add("POST /properties/{pid}/rentals", new PostPropertiesRentals());
		
		HashMap <String,String> map = new HashMap<String, String>(); 

		ICommand cmd = gest.find(args,map);
		ArrayList<String> result = cmd.execute(map);
		Output.Print(result, map);		
	}
	

	public static <E> void printArrayList(ArrayList<E> list){
		for (int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).toString());
		}
		System.out.println();
	}
}


