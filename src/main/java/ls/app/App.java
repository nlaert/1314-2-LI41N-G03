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
import ls.commands.result.PropertiesRentalsByYearResult;
import ls.commands.result.PropertiesRentalsResult;
import ls.commands.result.PropertiesRentalsWithDateResult;
import ls.commands.result.PropertiesResult;
import ls.commands.result.PropertyDetailsResult;
import ls.commands.result.RentalsResult;
import ls.commands.result.UserRentalsResult;
import ls.commands.result.UserUsernameResult;
import ls.commands.result.UsersResult;
import ls.commands.users.GetUserUsername;
import ls.commands.users.GetUsers;
import ls.commands.users.PostUsers;
import ls.db.IType;
import ls.exception.AppException;
import ls.exception.IllegalCommandException;
import ls.http.server.ServerHTTP;
import ls.output.Output;
import ls.output.html.view.PropertiesRentalsByYearView;
import ls.output.html.view.PropertiesRentalsView;
import ls.output.html.view.PropertiesRentalsWithDateView;
import ls.output.html.view.PropertiesView;
import ls.output.html.view.PropertyDetailsView;
import ls.output.html.view.RentalView;
import ls.output.html.view.UserRentalsView;
import ls.output.html.view.UserUsernameView;
import ls.output.html.view.UsersView;
import ls.propertiesRental.Rental;
import ls.utils.Utils;

public class App {
	
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users/joao

	public static Rental gest;
	private static ServerHTTP server;
//	private static HashMap<ICommandResult,ITypeView> commandsResults;
	public static void main(String[] args) throws Exception
	{	
		try {
			gest = new Rental();
//			commandsResults = new HashMap<ICommandResult,ITypeView>();
			addCommands();
			addViews();
			
			if (args.length<1)
				prompt();
			else
				executeCommand(args);
		} catch (AppException e) {
			e.getMessage();
		}
				
	}
	private static void startServer(String [] command) throws Exception
	{
		HashMap <String,String> map = new HashMap<String, String>(); 
		map = Utils.mapper(command[2], map);
		Integer port;
		try{
			port = Integer.parseInt(map.get("port"));
		}
		catch(NumberFormatException e){
			throw new IllegalCommandException("invalid Port number");
		}
		server = new ServerHTTP(gest, port);
		server.initServer();
	}
	
	private static void prompt() throws Exception{
		Scanner k = new Scanner(System.in);
		System.out.println("Enter a command");
		String in = "";
		while (!(in = k.nextLine()).equals("EXIT")){
			try {
				executeCommand(in.split(" "));
			} 
			catch (AppException e) {
				e.getMessage();
			}
			System.out.println("Enter a command");
		}
		k.close();
		if (server != null)
			server.stopServer();
		System.exit(0);
		
	}
	

	private static void executeCommand(String [] command) throws Exception{//TODO move to Rental
		if (command[0].equals("OPTION"))
			gest.printCommands();
		else if(command[0].contains("LISTEN"))
			startServer(command);
		else{
			HashMap <String,String> map = new HashMap<String, String>(); 
	
			ICommand<IType> cmd = gest.find(command,map);
			ICommandResult<IType> result = cmd.execute(map);
			Output.Print(result, map, gest);
		}
	}


	public static void addCommands() throws IllegalCommandException{
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
	
	public static void addViews()
	{
		gest.addView(UsersResult.class, UsersView.class);
		gest.addView(UserUsernameResult.class, UserUsernameView.class);	
		
		gest.addView(PropertiesResult.class, PropertiesView.class);
		gest.addView(PropertyDetailsResult.class, PropertyDetailsView.class);
		
		gest.addView(RentalsResult.class, RentalView.class);
		gest.addView(PropertiesRentalsByYearResult.class, PropertiesRentalsByYearView.class);
		gest.addView(PropertiesRentalsResult.class, PropertiesRentalsView.class);
		gest.addView(PropertiesRentalsWithDateResult.class, PropertiesRentalsWithDateView.class);
		gest.addView(UserRentalsResult.class, UserRentalsView.class);
	}
}


