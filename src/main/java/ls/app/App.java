package ls.app;


import java.util.HashMap;
import java.util.Scanner;

import ls.commands.ICommand;
import ls.commands.properties.DeletePropertiesPid;
import ls.commands.properties.GetProperties;
import ls.commands.properties.GetPropertiesDetails;
import ls.commands.properties.GetPropertiesLocation;
import ls.commands.properties.GetPropertiesOwner;
import ls.commands.properties.GetPropertiesType;
import ls.commands.properties.PostProperties;
import ls.commands.rentals.DeletePropertiesRental;
import ls.commands.rentals.GetPropertiesRentals;
import ls.commands.rentals.GetPropertiesRentalsByYear;
import ls.commands.rentals.GetPropertiesRentalsWithDate;
import ls.commands.rentals.GetUsersRentals;
import ls.commands.rentals.PatchPropertiesRentals;
import ls.commands.rentals.PostPropertiesRentals;
import ls.commands.result.ICommandResult;
import ls.commands.users.GetUserUsername;
import ls.commands.users.GetUsers;
import ls.commands.users.PostUsers;
import ls.db.IType;
import ls.exception.AppException;
import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;
import ls.http.server.ServerHTTP;
import ls.output.Output;
import ls.propertiesRental.Rental;

public class App {
	
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users/joao

	private static Rental gest;
//	private static HashMap<ICommandResult,ITypeView> commandsResults;
	public static void main(String[] args) throws Exception
	{	
		try {
			gest = new Rental();
//			commandsResults = new HashMap<ICommandResult,ITypeView>();
			addCommands();
//			addViews();
			
			if (args.length<1)
				prompt();
			else
				executeCommand(args);
		} catch (AppException e) {
			e.getMessage();
		}
				
	}
	private static void startServer() throws Exception
	{
		System.out.println("Server Started!!!");
		new ServerHTTP(gest).initServer();
	
	}
	
	private static void prompt() throws Exception{
		Scanner k = new Scanner(System.in);
		System.out.println("Enter a command");
		String in = "";
		while (!(in = k.nextLine()).equals("EXIT")){
			if (in.equals("OPTION /"))
				gest.printCommands();
			else if(in.contains("LISTEN /"))
				startServer();
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

		ICommand<IType> cmd = gest.find(command,map);
		ICommandResult<IType> result = cmd.execute(map);
		
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
		gest.add("GET /users/{username}/properties/owned", new GetPropertiesOwner());
		gest.add("GET /properties/{pid}/rentals", new GetPropertiesRentals());
		gest.add("GET /properties/{pid}/rentals/{year}/{cw}", new GetPropertiesRentalsWithDate());
		gest.add("GET /properties/{pid}/rentals/{year}", new GetPropertiesRentalsByYear());
		gest.add("POST /users", new PostUsers());
		gest.add("POST /properties", new PostProperties());
		gest.add("POST /properties/{pid}/rentals", new PostPropertiesRentals());
		gest.add("PATCH /properties/{pid}/rentals/{year}/{cw}", new PatchPropertiesRentals());
		gest.add("DELETE /properties/{pid}/rentals/{year}/{cw}", new DeletePropertiesRental());
		gest.add("DELETE /properties/{pid}", new DeletePropertiesPid());
	}
	
//	private static void addViews()
//	{
//		gest.addView(new UsersResult(), new UsersView());
//		gest.addView(new PropertiesResult(), new PropertiesView());
//	}
}


