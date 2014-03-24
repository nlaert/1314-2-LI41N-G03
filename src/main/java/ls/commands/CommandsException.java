package ls.commands;
import java.sql.SQLException;
public class CommandsException extends SQLException{


	
	public CommandsException(String message)
	{
		super(message);
	}
	
	public CommandsException(String message, Throwable e)
	{
		super(message,e);
	}
	
	public CommandsException(Throwable e)
	{
		super(e);
	}
}
