package ls.commands;


public class Commands {

	public String path;
	public int size;
	public ICommand command;

	
	public Commands(String path, int size, ICommand command)
	{
		this.path = path;
		this.size = size;
		this.command = command;
	}

	
	
}
