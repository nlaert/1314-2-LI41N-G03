package ls.exception;

public class ServerHttpException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServerHttpException(String message)
	{
		super(message);
	}
	
	public ServerHttpException(String message, Throwable e)
	{
		super(message,e);
	}
	
	public ServerHttpException(Throwable e)
	{
		super(e);
	}
}
