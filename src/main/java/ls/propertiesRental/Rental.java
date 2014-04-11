package ls.propertiesRental;

import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.Commands;
import ls.commands.iCommand;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.utils.*;

public class Rental {

	
	private ArrayList<Commands> list;
	public Rental() throws IllegalCommandException, ConnectionDatabaseException
	{
		list = new ArrayList<Commands>();
		
	}

	public void add(String command, iCommand e) throws IllegalCommandException
	{
		//necessario verificar se ja existe ?
		list.add(new Commands(command, command.split("/").length, e));
		
		
	}

	public iCommand find(String command) throws IllegalCommandException
	{

		if(command.equals(""))
			throw new IllegalCommandException("Empty command");
		command = Utils.removeParameterList(command);
		String [] arraySplit = command.split("/");
		for(int i = 0; i<list.size();i++)
		{
			boolean fail = false;
			if(list.get(i).size == arraySplit.length)
			{
				String []aux = list.get(i).path.split("/");
				for(int j = 0; j<aux.length; j++)
				{
					if(!aux[j].equals(arraySplit[j]) && !aux[j].contains("{"))
					{
						fail = true;
						break;
					}
					
				}
				if(!fail)
					return list.get(i).command;
			}
		}
		throw new IllegalCommandException("Invalid command");

	}
}
