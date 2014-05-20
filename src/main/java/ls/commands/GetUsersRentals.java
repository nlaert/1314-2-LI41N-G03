package ls.commands;

import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.db.UsersDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class GetUsersRentals extends CommandsUtils implements ICommand<User> {

	
	@Override
	public ArrayList<User> execute(HashMap<String, String> map) throws IllegalCommandException, ConnectionDatabaseException {
		return UsersDB.GetUsersRentals(map);
	}
}
