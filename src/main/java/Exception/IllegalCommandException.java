package Exception;
public class IllegalCommandException extends AppException{


	
	public IllegalCommandException(String message)
	{
		super(message);
	}
	
	public IllegalCommandException(String message, Throwable e)
	{
		super(message,e);
	}
	
	public IllegalCommandException(Throwable e)
	{
		super(e);
	}
}
