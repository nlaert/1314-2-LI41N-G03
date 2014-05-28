package ls.commands;

public class Property implements IType {
	public int pid;
	public String type;
	public String description;
	public int price;
	public String location;
	public User owner;
	
	public Property(int pid, String type, String description, int price, String location, User owner){
		this.pid = pid;
		this.type = type;
		this.description = description;
		this.price = price;
		this.location = location;
		this.owner = owner;
	}

	@Override
	public String toString() {
		return pid + "\t" + type + "\t" + description + "\t" + price + "\t" + location
				+ "\t" + owner.username;
	}

	@Override
	public String[] getColumNames() {
		return new String [] {"pid", "type", "description", "price", "location", "owner"};
	}
}
