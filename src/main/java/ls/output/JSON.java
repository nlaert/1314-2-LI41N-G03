package ls.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import ls.commands.result.ICommandResult;
import ls.db.IType;
import ls.exception.FileException;


public class JSON {
	public static void jsonify(ICommandResult<IType> commandResult, HashMap<String, String> map) throws FileException{
		
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
		if(map.containsKey("output-file")){
			printToFile(map.get("output-file"), result.toString());
		}
		else
		{
			System.out.print(result.toString());
		}
	}

	
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	private static void printToFile(String filename, String result) throws FileException {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(filename));
			bw.write(result);
			bw.close();
		} catch (IOException e) {
			throw new FileException("Could not write to the file", e);
		}	
	}
}
