package ls.commands;

import java.util.ArrayList;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;


public interface iCommand {
	

	public ArrayList<String> execute(String command) 
			throws  IllegalCommandException, ConnectionDatabaseException ;
}
