package Exception;
import java.sql.SQLException;
public class CommandsException extends AppException{


	
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
