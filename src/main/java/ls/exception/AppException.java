package ls.exception;

public class AppException  extends Exception{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AppException(String message){
		super(message);
		System.out.println(message);
	}


	public AppException(String message, Throwable cause){
		super(message,cause);
		System.out.println(message+": "+cause.getMessage());

	}
	public AppException(Throwable cause){
		super(cause);
		System.out.println(cause.getMessage());
		
	}

}
