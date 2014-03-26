package ls.propertiesRental;

import java.sql.SQLException;
import java.util.HashMap;

import Exception.CloseConnectionException;
import Exception.ClosingDataAccessException;
import Exception.CommandsException;
import Exception.IllegalCommandException;
import ls.commands.GetProperties;
import ls.commands.GetUsers;
import ls.commands.PostProperties;
import ls.commands.PostUsers;
import ls.commands.iCommand;
import ls.utils.*;

public class Commands {

	private HashMap<String,iCommand> map;
	private String command;

	public Commands() throws CommandsException, ClosingDataAccessException, CloseConnectionException
	{
		map = new HashMap<String,iCommand>();
	}

	public void add(String command, iCommand e)
	{
		command=Utils.limitatorPath(command);
		if(!map.containsKey(command))
			map.put(command, e);
	}

	public iCommand find(String command) throws IllegalCommandException
	{
		if(command.equals(""))
			return null;
		command = Utils.limitatorPath(command);
		if(map.containsKey(command))
		{
			return map.get(command);
		}
		else{
			throw new IllegalCommandException("Comando errado!");
		}

	}



}
