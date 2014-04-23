package ls.output;

import java.util.ArrayList;

public class JSON {
	public static <E> String jsonify(ArrayList<E> params){
		StringBuilder result = new StringBuilder("{");
//		if (params.size()>1)
		
		return null;
	}

	
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
}
