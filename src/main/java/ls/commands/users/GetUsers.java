package ls.commands.users;

import java.util.HashMap;

import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.commands.result.UsersResult;
import ls.db.User;
import ls.db.UsersDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class GetUsers implements ICommand<User> {

	
	@Override
	public ICommandResult<User> execute(HashMap<String, String> map)
			throws IllegalCommandException, ConnectionDatabaseException {
	
		return new UsersResult(UsersDB.getAll());
		
	}

}