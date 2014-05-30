package ls.commands.result;

import java.util.ArrayList;

import ls.db.Property;
import ls.db.User;

public class UserUsernameResult implements  ICommandResult<User> {

	private ArrayList<User> users;
	private ArrayList<Property> properties;
	
	public UserUsernameResult(ArrayList<User> users, ArrayList<Property> properties)
	{
		this.users = users;
		this.properties = properties;
	}
	

	public ArrayList<User> getUsers()
	{
		return users;
	}
	
	public ArrayList<Property> getProperties()
	{
		return properties;
	}


	@Override
	public int getSize() {
		return users.size();
	}


	@Override
	public ArrayList<User> getArrayList() {
		return users;
	}
}
