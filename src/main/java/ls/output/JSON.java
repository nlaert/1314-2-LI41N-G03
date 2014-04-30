package ls.output;

import java.util.ArrayList;

//[{"username":"nick", "password":"ls1314", "email":"a35466@alunos.isel.pt", "fullname":"Nick Laert"},
//{"username":"joao", "password":"ls1314", "email":"a35392@alunos.isel.pt", "fullname":"Joao Rodrigues"}]

public class JSON {
	public static String jsonify(ArrayList<String> params){
		if (params==null || params.size()==0)
			return "{}";
		String [] columnsNames = params.get(0).split("\t");
		StringBuilder result = new StringBuilder();
		if (params.size()>2)
			result.append("[");
		for (int i = 1; i < params.size(); i++){
			result.append("{");
			String [] row = params.get(i).split("\t");
			for (int j = 0; j<row.length; j++){
				result.append("\"" + columnsNames[j] + "\":");
				if(isNumeric(row[j]))
					result.append(row[j]);
				else
					result.append("\""+ row[j] + "\"");
				if(j!=row.length-1)
					result.append(",");
			}
			if(i!=params.size()-1)
				result.append("},");
		}
		
		result.append("}");
		if (params.size()>2)
			result.append("]");
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
