

import ls.commands.*;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.propertiesRental.Commands;
import ls.utils.Utils;

public class App {

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
		
		String command = Utils.argsToString(args);
		iCommand cmd = gest.find(command);
		Utils.printArrayList(cmd.execute(command));
	}
}


