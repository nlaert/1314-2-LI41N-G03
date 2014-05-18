package ls.commands;

public class Property {
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
		return "pid=" + pid + ", type=" + type + ", description="
				+ description + ", price=" + price + ", location=" + location
				+ ", owner=" + owner.username;
	}
}
