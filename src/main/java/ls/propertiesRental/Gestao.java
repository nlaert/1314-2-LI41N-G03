package ls.propertiesRental;

import java.sql.SQLException;
import java.util.HashMap;

import Exception.ClosingDataAccessException;
import Exception.CommandsException;
import Exception.IllegalCommandException;
import ls.commands.GetProperties;
import ls.commands.GetUsers;
import ls.commands.PostProperties;
import ls.commands.PostUsers;
import ls.commands.iCommand;
import ls.utils.*;

public class Gestao {

	private HashMap<String,iCommand> map;
	private String command;
	
	public Gestao(String command) throws CommandsException, ClosingDataAccessException
	{
		this.command = command;
		map = new HashMap<String,iCommand>();
		map.put("GET /users",new GetUsers());
		map.put("GET /properties", new GetProperties());
		map.put("POST /users", new PostUsers());
		map.put("POST /properties", new PostProperties());
		run();
		
	}
	
	
	
	private void run() throws  ClosingDataAccessException, CommandsException {
		if(command.equals(""))
			System.exit(0);
		String [] keyValue = Utils.limitator(command);
		if(map.containsKey(keyValue[0]))
		{
			map.get(keyValue[0]).execute(keyValue[1]);
		}
		else{
			throw new IllegalCommandException("Comando errado!");
		}
		
		
	}



	
}
