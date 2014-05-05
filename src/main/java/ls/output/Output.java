package ls.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.FileException;

public class Output {
	
	private static String html = "text/html", json = "application/json";
	

	public static void Print(ArrayList<String> params, HashMap <String, String> map) throws FileException{
		String result = "", accept = "";
		if (map.containsKey("accept"))
			accept = map.get("accept");
		if (accept.equalsIgnoreCase(html))
			result = HTML.htmlify(params);
		else if (accept.equalsIgnoreCase(json))
			result = JSON.jsonify(params);
		else
			result = printArrayList(params);
		
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

	public static <E> String printArrayList(ArrayList<E> list){
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < list.size(); i++)
			str.append(list.get(i).toString() + "\n");
		return str.toString();
	}

}
