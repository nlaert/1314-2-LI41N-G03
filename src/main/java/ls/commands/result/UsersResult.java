package ls.commands.result;

import java.util.ArrayList;

import ls.db.User;

public class UsersResult implements ICommandResult<User> {

	private ArrayList<User> users;
	
	public UsersResult(ArrayList<User> users)
	{
		this.users = users;
		
	}
	
	public ArrayList<User> getUsers()
	{
		return users;
	}

	public ArrayList<User> getArrayList()
	{
		return users;
	}
	
	public int getSize()
	{
		return users.size();
	}
}
