package ls.exception;

public class EmptyResultSetException extends AppException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmptyResultSetException (String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public EmptyResultSetException(String message, Throwable e) {
		super(message,e);
		// TODO Auto-generated constructor stub
	}
	public EmptyResultSetException(Throwable e) {
		super(e);
		// TODO Auto-generated constructor stub
	}
	

}
