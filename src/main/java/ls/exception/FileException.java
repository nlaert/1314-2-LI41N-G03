package ls.exception;

public class FileException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileException(String message) {
		super(message);
		
	}
	public FileException(String message, Throwable e)
	{
		super(message,e);
	}
	
	public FileException(Throwable e)
	{
		super(e);
	}

}
