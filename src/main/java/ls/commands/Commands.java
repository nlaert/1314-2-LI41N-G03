package ls.commands;


public class Commands<E> {

	public String path;
	public int size;
	public ICommand<E> command;

	

	public  Commands(String path, int size, ICommand<E> command)
	{
		this.path = path;
		this.size = size;
		this.command = command;
	}

	
	
}
