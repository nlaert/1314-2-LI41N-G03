package ls.commands;


public class Commands {

	public String path;
	public int size;
	public iCommand command;

	
	public Commands(String path, int size, iCommand command)
	{
		this.path = path;
		this.size = size;
		this.command = command;
	}

	
	
}
