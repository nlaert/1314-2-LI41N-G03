package ls.propertiesRental;

import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.Commands;
import ls.commands.ICommand;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.utils.*;

public class Rental {

	
	private ArrayList<Commands> list;
	public Rental() throws IllegalCommandException, ConnectionDatabaseException
	{
		list = new ArrayList<Commands>();
		
	}

	public void add(String command, ICommand e) throws IllegalCommandException
	{
		//necessario verificar se ja existe ?
		list.add(new Commands(command, command.split("/").length, e));
		
		
	}

	public ICommand find(String[] command, HashMap<String, String> map) throws IllegalCommandException
	{
		if(command == null || command.length<2)
			throw new IllegalCommandException("invalid command");
		
		for(int i = 0; i< list.size(); i++)
		{
			String [] commandPath = command[1].split("/");  //path recebida
			if(commandPath.length == list.get(i).size) // avaliar o tamanho
			{
				String aux = list.get(i).path.substring(0,list.get(i).path.indexOf(" "));
				if(aux.equals(command[0])) // avaliar se é GET ou POST
				{
					String[] listPath = list.get(i).path.split(" ");
					if(comparePath(listPath[1].split("/"),commandPath,map))
					{
						if(command.length == 3)
							map = Utils.mapper(command[2], map);
						return list.get(i).command;
					}
						
						
				}
			}
		}
		throw new IllegalCommandException("Invalid command");

	}

	public boolean comparePath(String [] listPath, String [] commandPath, HashMap<String,String> map)
	{
		if(listPath.length != commandPath.length)
			return false;
		
		for(int i = 0; i<listPath.length; i++)
		{
			if(!listPath[i].equals(commandPath[i]))
			{
				if(!listPath[i].contains("{"))
					return false;
				else{
					String key = listPath[i].substring(1,listPath[i].length()-1);
					map.put(key, commandPath[i]);
				}
			}
		}
		return true;
	}
}














//String [] arraySplit = command[1].split("/");
//
//for(int i = 0; i<list.size();i++)
//{
//	boolean fail = false;
//	if(list.get(i).size == arraySplit.length)
//	{
//		String []aux = list.get(i).path.split(" ");
//		for(int j = 0; j<aux.length; j++)
//		{
//			if(!aux[j].equals(arraySplit[j]) && !aux[j].contains("{"))
//			{
//				fail = true;
//				break;
//			}
//			
//		}
//		if(!fail)
//			return list.get(i).command;
//	}
//}