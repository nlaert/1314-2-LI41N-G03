package ls.commands;

import java.util.HashMap;

import ls.commands.result.ICommandResult;
import ls.exception.AuthenticationException;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;


public interface ICommand<IType> {
	

	public ICommandResult<IType> execute(HashMap<String, String> map) 
			throws  IllegalCommandException, ConnectionDatabaseException, AuthenticationException ;
	
}
