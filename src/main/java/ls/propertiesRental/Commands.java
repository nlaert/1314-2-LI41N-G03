package ls.propertiesRental;

import java.util.HashMap;
import Exception.IllegalCommandException;
import Exception.ConnectionDatabaseException;
import ls.commands.iCommand;
import ls.utils.*;

public class Commands {

	private HashMap<String,iCommand> map;
	public Commands() throws IllegalCommandException, ConnectionDatabaseException
	{
		map = new HashMap<String,iCommand>();
	}

	public void add(String command, iCommand e) throws IllegalCommandException
	{
		command=Utils.limitatorPath(command);
		if(!map.containsKey(command))
			map.put(command, e);
	}

	public iCommand find(String command) throws IllegalCommandException
	{
		
		if(command.equals(""))
			throw new IllegalCommandException("Comando errado!");
		String path = Utils.limitatorPath(command);
		if(map.containsKey(path))
		{
			return map.get(path);
		}
		else{
			throw new IllegalCommandException("Comando errado!");
		}

	}



}
