package ls.commands;

public class User implements IType {

	public String username;
	public String password;
	public String email;
	public String fullName;
	
	public User(String username, String password, String email, String fullName){
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return username + "\t" + password + "\t"+  email + "\t" + fullName;
	}
	@Override
	public String[] getColumNames()
	{
		return new String[] {"Username", "Password", "Email", "FullName"};
		
	}
}
