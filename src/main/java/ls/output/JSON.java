package ls.output;

import java.util.ArrayList;

//[{"username":"nick", "password":"ls1314", "email":"a35466@alunos.isel.pt", "fullname":"Nick Laert"},
//{"username":"joao", "password":"ls1314", "email":"a35392@alunos.isel.pt", "fullname":"Joao Rodrigues"}]

public class JSON {
	public static String jsonify(ArrayList<String> params){
		String [] columns = params.get(0).split("\t");
		StringBuilder result = new StringBuilder();
		if (params.size()>2)
			result.append("[");
		else
			result.append("{");
		for (int i = 1; i < params.size(); i++){
			String [] aux = params.get(i).split("\t");
			
		}
		
		
		return result.toString();
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
