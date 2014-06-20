package ls.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import ls.commands.result.ICommandResult;
import ls.db.IType;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;
import ls.http.response.HttpContent;
import ls.output.html.view.HtmlView;
import ls.output.json.view.JsonView;
import ls.rentalManager.RentalManager;

public class Output {
	
	private static String html = "text/html", json = "application/json";
	
	public static void Print(ICommandResult<IType> commandResult, HashMap <String, String> map, RentalManager gest) throws FileException, IOException, IllegalCommandException{
		
		String accept = "";
		if (map.containsKey("accept"))
			accept = map.get("accept");
		
		if (accept.equalsIgnoreCase(html))
		{
			HtmlView<IType> view;
			HttpContent page;
			view = gest.getView();

			page = view.getView(commandResult, map);
			send(page, map);


		}
		else if (accept.equalsIgnoreCase(json))
		{
			send(new JsonView(commandResult,map),map);
		}
		else
		{
			System.out.println(printArrayList(commandResult));
		}
	}

	public static <E> String printArrayList(ICommandResult<IType> commandResult){
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < commandResult.getSize(); i++)
			str.append(commandResult.getArrayList().get(i).toString() + "\n");
		return str.toString();
	}
	
	public static void send(HttpContent content,HashMap <String, String> map) throws IOException, FileException {
		BufferedWriter writer = null;
        try{
        	if(map.containsKey("output-file"))
        		writer = new BufferedWriter(new FileWriter(map.get("output-file")));
        	else
        		writer = new BufferedWriter(new OutputStreamWriter(System.out));
        	content.writeTo(writer);
        	writer.newLine();
        }
        catch(IOException e){
        	throw new FileException("Could not write to the file");
        }
        finally{
        		writer.flush();
        }   
    }
}
