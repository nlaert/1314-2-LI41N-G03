package ls.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import ls.commands.result.ICommandResult;
import ls.db.IType;
import ls.exception.FileException;

public class Output {
	
	private static String html = "text/html", json = "application/json";
	

	public static void Print(ICommandResult<IType> commandResult, HashMap <String, String> map) throws FileException{
		String result = "", accept = "";
		if (map.containsKey("accept"))
			accept = map.get("accept");
//		if (accept.equalsIgnoreCase(html))
//			result = HTMLantigo.htmlify(commandResult);
//		else if (accept.equalsIgnoreCase(json))
//			result = JSON.jsonify(commandResult);
//		else
			result = printArrayList(commandResult);
		
		if (map.containsKey("output-file"))
			printToFile(map.get("output-file"), result);
		else
			System.out.println(result);			
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

	public static <E> String printArrayList(ICommandResult<IType> commandResult){
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < commandResult.getSize(); i++)
			str.append(commandResult.getArrayList().get(i).toString() + "\n");
		return str.toString();
	}

}
