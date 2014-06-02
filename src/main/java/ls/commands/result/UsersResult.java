package ls.commands.result;

import java.util.ArrayList;

import ls.db.User;

public class UsersResult implements ICommandResult<User> {

	private ArrayList<User> users;
	
	public UsersResult(ArrayList<User> users)
	{
		this.users = users;
		
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
