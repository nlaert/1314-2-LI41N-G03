package ls.commands.users;

import java.util.HashMap;

import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.commands.result.UserUsernameResult;
import ls.db.PropertiesDB;
import ls.db.User;
import ls.db.UsersDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class GetUserUsername  implements ICommand<User> {


	@Override
	public ICommandResult<User> execute(HashMap<String, String> map) throws IllegalCommandException, ConnectionDatabaseException {
		return new UserUsernameResult( UsersDB.getUserByUsername(map),PropertiesDB.getPropertiesOwner(map));
		
		
	}
}




