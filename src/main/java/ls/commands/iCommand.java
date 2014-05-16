package ls.commands;

import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;


public interface ICommand<E> {
	

	public ArrayList<E> execute(HashMap<String, String> map) 
			throws  IllegalCommandException, ConnectionDatabaseException ;
	
}
