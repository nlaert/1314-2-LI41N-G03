package ls.output.json.view;

import java.util.HashMap;

import ls.commands.result.ICommandResult;
import ls.db.IType;
import ls.output.json.JsonPage;
import ls.output.json.JsonText;

public class JsonView extends JsonPage{

	public JsonView(ICommandResult<IType> result, HashMap<String, String> map)  {
		super(new JsonText(jsonify(result,map)));
		
	}

	public static String jsonify(ICommandResult<IType> commandResult, HashMap<String, String> map) {
		
		if (commandResult==null || commandResult.getSize()==0)
			System.out.println("{}"); 
		String [] columnsNames = commandResult.getArrayList().get(0).getColumNames();
		StringBuilder result = new StringBuilder();
		if (commandResult.getSize()>=2)
			result.append("[");
		for (int i = 0; i < commandResult.getSize(); i++){
			result.append("{");
			String [] row = commandResult.getArrayList().get(i).toString().split("\t");
			for (int j = 0; j<row.length; j++){
				result.append("\"" + columnsNames[j] + "\":");
				if(isNumeric(row[j]))
					result.append(row[j]);
				else
					result.append("\""+ row[j] + "\"");
				if(j!=row.length-1)
					result.append(",");
			}
			if(i!=commandResult.getSize()-1)
				result.append("},");
		}
		
		result.append("}");
		if (commandResult.getSize()>=2)
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
