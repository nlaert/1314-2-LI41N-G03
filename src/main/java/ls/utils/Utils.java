package ls.utils;

public class Utils {

	public static  String [] limitator(String command)
	{
		String [] divide = new String[2];
		int first = command.indexOf("/");
		if(first == command.lastIndexOf("/"))
		{
			divide[0] = command;
			divide[1] = "";
		}
		else
		{
		
			int aux = command.substring(first+1).indexOf("/") + first;
			divide[1] = command.substring(aux+1);
			divide[0] = command.substring(0,aux+1);
		}
		return divide;
	}
}
