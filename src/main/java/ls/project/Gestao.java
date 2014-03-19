package ls.project;

import java.util.HashMap;

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
		
		String key = limitator();
		if(map.containsKey(key))
		{
			map.get(key).executa(second);
		}
		//falta se nao conter
		
	}



	public  String limitator()
	{
		int first = command.indexOf("/");
		if(first == command.lastIndexOf("/"))
		{
			return command;
		}
		else
		{
		
			int aux = command.substring(first+1).indexOf("/") + first;
			second = command.substring(aux+1);
			return command.substring(0,aux+1);
		}
	}
}
