package ls.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.db.UsersDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;

public class GetUsers implements ICommand<User> {

	


	@Override
	public ArrayList<User> execute(HashMap<String, String> map) throws IllegalCommandException, ConnectionDatabaseException {
		
			UsersDB usersDB = new UsersDB(map); 
			ArrayList<User> users = usersDB.getAll();
			return users;
			
	}
}