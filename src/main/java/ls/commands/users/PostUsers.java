package ls.commands.users;

import java.util.HashMap;

import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.commands.result.StringResult;
import ls.db.UsersDB;
import ls.exception.AuthenticationException;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class PostUsers implements ICommand<String> {

	
	@Override
	public ICommandResult<String> execute(HashMap<String, String> map) throws IllegalCommandException,ConnectionDatabaseException, AuthenticationException {
		
		return new StringResult(UsersDB.PostUser(map));
	}

}
