package ls.commands;

import java.util.ArrayList;
import Exception.IllegalCommandException;
import Exception.ConnectionDatabaseException;


public interface iCommand {
	

	public ArrayList<String> execute(String command) 
			throws  IllegalCommandException, ConnectionDatabaseException ;
}
