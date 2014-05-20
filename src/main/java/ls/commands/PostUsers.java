package ls.commands;

import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.db.UsersDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class PostUsers extends CommandsUtils implements ICommand<String> {

	
	@Override
	public ArrayList<String> execute(HashMap<String, String> map) throws IllegalCommandException,ConnectionDatabaseException {
		
		return UsersDB.PostUser(map);
		
		
	}

}
