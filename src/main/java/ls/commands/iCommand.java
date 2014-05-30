package ls.commands;

import java.util.HashMap;

import ls.commands.result.ICommandResult;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;


public interface ICommand<E> {
	

	public ICommandResult<E> execute(HashMap<String, String> map) 
			throws  IllegalCommandException, ConnectionDatabaseException ;
	
}
