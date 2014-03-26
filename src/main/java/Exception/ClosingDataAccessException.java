package Exception;

public class ClosingDataAccessException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClosingDataAccessException(String message)
	{
		super(message);
	}
	
	public ClosingDataAccessException(String message, Throwable e)
	{
		super(message,e);
	}
	
	public ClosingDataAccessException(Throwable e)
	{
		super(e);
	}
}
