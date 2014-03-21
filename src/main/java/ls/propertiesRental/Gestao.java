package ls.propertiesRental;

import java.sql.SQLException;
import java.util.HashMap;

import ls.commands.GetProperties;
import ls.commands.GetUsers;
import ls.commands.PostUsers;
import ls.utils.*;

public class Gestao {

	private HashMap<String,iCommand> map;
	private String command;
	
	public Gestao(String command) throws SQLException
	{
		this.command = command;
		map = new HashMap<String,iCommand>();
		map.put("GET /users",new GetUsers());
		map.put("GET /properties", new GetProperties());
		map.put("POST /users", new PostUsers());
		run();
		
	}
	
	
	
	private void run() throws SQLException {
		if(command.equals(""))
			System.exit(0);
		String [] keyValue = Utils.limitator(command);
		if(map.containsKey(keyValue[0]))
		{
			map.get(keyValue[0]).execute(keyValue[1]);
		}
		else
		{
			System.out.println("Nao existe informacao");
		}
		
		
		
	}



	
}
