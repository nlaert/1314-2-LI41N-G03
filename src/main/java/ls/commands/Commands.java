package ls.commands;



public class Commands<IType> {

	public String path;
	public int size;
	public ICommand<IType> command;

	

	public  Commands(String path, int size, ICommand<IType> command)
	{
		this.path = path;
		this.size = size;
		this.command = command;
	}

	
	
}
