package Exception;

public class CloseConnectionException extends AppException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CloseConnectionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public CloseConnectionException(String message, Throwable e) {
		super(message,e);
		// TODO Auto-generated constructor stub
	}
	public CloseConnectionException(Throwable e) {
		super(e);
		// TODO Auto-generated constructor stub
	}
}
