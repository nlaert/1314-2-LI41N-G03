package ls.exception;

public class AuthenticationException extends AppException {

	private static final long serialVersionUID = 1L;
	
	public AuthenticationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public AuthenticationException(String message, Throwable e) {
		super(message,e);
		// TODO Auto-generated constructor stub
	}
	public AuthenticationException(Throwable e) {
		super(e);
		// TODO Auto-generated constructor stub
	}
}
