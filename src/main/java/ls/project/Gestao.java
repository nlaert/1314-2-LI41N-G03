package ls.project;

import java.util.HashMap;
import java.util.NoSuchElementException;

import ls.project.get.GetProperties;
import ls.project.get.GetUsers;
import ls.project.get.iGet;
import ls.utils.*;

public class Gestao {

	private HashMap<String,iGet> map;
	private String command;
	private String second;
	
	public Gestao(String command)
	{
		this.command = command;
		map = new HashMap<String,iGet>();
		map.put("GET /users",new GetUsers());
		map.put("GET /properties", new GetProperties());
		run();
		
	}
	
	
	
	private void run() {
		if(command.equals(""))
			System.exit(0);
		String [] keyValue = Utils.limitator(command);
		if(map.containsKey(keyValue[0]))
		{
			map.get(keyValue[0]).executa(keyValue[1]);
		}
		else
		{
			System.out.println("Nao existe informacao");
		}
		
		//falta se nao conter
		
	}



	
}
