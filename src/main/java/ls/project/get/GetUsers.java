package ls.project.get;

import java.util.HashMap;


import ls.utils.Utils;

public class GetUsers implements iGet {

	private HashMap<String,iGet> map;
	
	public GetUsers()
	{
		map = new HashMap<String,iGet>();
		map.put("/",new GetUserUsername());
		map.put("", new GetUserUsername());
	}

	@Override
	public void executa(String command) {
		String [] keyValue = Utils.limitator(command);
		if(map.containsKey(keyValue[0]))
		{
			map.get(keyValue[0]).executa(keyValue[1]);
		}
		else
		{
			keyValue[1] = keyValue[0].substring(1);
			keyValue[0] = keyValue[0].substring(0,1);
			if(map.containsKey(keyValue[0]))
			{
				map.get(keyValue[0]).executa(keyValue[1]);
			}
			
		}
		

	}

}
