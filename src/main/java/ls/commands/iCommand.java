package ls.commands;

import java.util.ArrayList;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;


public interface ICommand {
	

	public ArrayList<String> execute(String[] command) 
			throws  IllegalCommandException, ConnectionDatabaseException ;
}
