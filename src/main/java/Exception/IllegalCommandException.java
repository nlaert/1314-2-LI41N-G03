package Exception;


@SuppressWarnings("serial")
public class IllegalCommandException extends CommandsException {

	
	public IllegalCommandException(String msg){
		super(msg);
	}
	public IllegalCommandException(String msg,Throwable cause){
		super(msg,cause);
	}
	public IllegalCommandException(Throwable cause){
		super(cause);
	}
}
