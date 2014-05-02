package ls.app;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import ls.commands.*;
import ls.exception.AppException;
import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;
import ls.output.Output;
import ls.propertiesRental.Rental;

public class App {
	
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users/joao

	private static Rental gest;
	public static void main(String[] args)
	{	
		try {
			gest = new Rental();
			addCommands();
			
			if (args.length<1)
				prompt();
			else
				executeCommand(args);
		} catch (AppException e) {
			e.getMessage();
		}
				
	}
	
	private static void prompt(){
		Scanner k = new Scanner(System.in);
		System.out.println("Enter a command");
		String in = "";
		while (!(in = k.nextLine()).equals("EXIT")){
			if (in.equals("OPTION /"))
				gest.printCommands();
			else
				try {
					executeCommand(in.split(" "));
				} catch (AppException e) {
					e.getMessage();
				}
			System.out.println("Enter a command");
		}
		k.close();
	}
	
	private static void executeCommand(String [] command) throws IllegalCommandException, ConnectionDatabaseException, FileException{
		HashMap <String,String> map = new HashMap<String, String>(); 

		ICommand cmd = gest.find(command,map);
		ArrayList<String> result = cmd.execute(map);
		Output.Print(result, map);
	}

	private static void addCommands() throws IllegalCommandException{
		gest.add("GET /users", new GetUsers());
		gest.add("GET /users/{username}", new GetUserUsername());
		gest.add("GET /properties", new GetProperties());
		gest.add("GET /properties/details/{pid}", new GetPropertiesDetails());
		gest.add("GET /properties/location/{location}", new GetPropertiesLocation());
		gest.add("GET /properties/owner/{owner}", new GetPropertiesOwner());
		gest.add("GET /properties/type/{type}", new GetPropertiesType());
		gest.add("GET /users/{username}/rentals", new GetUsersRentals());
		gest.add("GET /users/{username}/properties/owned", new GetUsersPropertiesOwned());
		gest.add("GET /properties/{pid}/rentals", new GetPropertiesRentals());
		gest.add("GET /properties/{pid}/rentals/{year}/{cw}", new GetPropertiesRentalsWithDate());
		gest.add("POST /users", new PostUsers());
		gest.add("POST /properties", new PostProperties());
		gest.add("POST /properties/{pid}/rentals", new PostPropertiesRentals());
		gest.add("PATCH /properties/{pid}/rentals/{year}/{cw}", new PatchPropertiesRentals());
		gest.add("DELETE /properties/{pid}/rentals/{year}/{cw}", new DeletePropertiesRental());
	}
}


