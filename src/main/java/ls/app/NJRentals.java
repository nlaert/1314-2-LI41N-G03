package ls.app;


import java.util.Scanner;

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
import ls.commands.result.PropertiesRentalsByYearResult;
import ls.commands.result.PropertiesRentalsResult;
import ls.commands.result.PropertiesRentalsWithDateResult;
import ls.commands.result.PropertiesResult;
import ls.commands.result.PropertyDetailsResult;
import ls.commands.result.RentalsResult;
import ls.commands.result.StringResult;
import ls.commands.result.UserRentalsResult;
import ls.commands.result.UserUsernameResult;
import ls.commands.result.UsersResult;
import ls.commands.users.GetUserUsername;
import ls.commands.users.GetUsers;
import ls.commands.users.PostUsers;
import ls.exception.AppException;
import ls.exception.IllegalCommandException;
import ls.output.html.view.PropertiesRentalsByYearView;
import ls.output.html.view.PropertiesRentalsView;
import ls.output.html.view.PropertiesRentalsWithDateView;
import ls.output.html.view.PropertiesView;
import ls.output.html.view.PropertyDetailsView;
import ls.output.html.view.RentalView;
import ls.output.html.view.StringView;
import ls.output.html.view.UserRentalsView;
import ls.output.html.view.UserUsernameView;
import ls.output.html.view.UsersView;
import ls.rentalManager.RentalManager;

public class NJRentals {
	
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users
//	java -cp target/classes:vendor/main/lib/sqljdbc4.jar ls.app.App GET /users/joao

	public static RentalManager gest;
	public static void main(String[] args) throws Exception
	{	
		try {
			gest = new RentalManager();
			addCommands();
			addViews();
			if (args.length<1)
				prompt();
			else
				gest.executeCommand(args);
		} catch (AppException e) {
			e.getMessage();
		}
				
	}
	
	
	private static void prompt() throws Exception{
		Scanner k = new Scanner(System.in);
		String in = "";
		while (true){
			System.out.println("Enter a command");
			in = k.nextLine();
			try {
				gest.executeCommand(in.split(" "));
			} 
			catch (AppException e) {
				e.getMessage();
			}
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
		gest.addView(StringResult.class, StringView.class);
	}
}


