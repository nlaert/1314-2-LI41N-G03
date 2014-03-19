package ls.project;

import java.util.HashMap;

public class GetUsers implements iGet {

	private HashMap<String,iGet> map;
	
	public GetUsers()
	{
		map = new HashMap<String,iGet>();
		map.put("/",new GetUsers());
	}

	@Override
	public void executa(String command) {
		if(command.equals(""))
			query();
		else
		{
			String key = command.substring(0,1);
			if(map.containsKey(key))
				map.get(key).executa(command.substring(1));
		}
		
	}

	private void query() {
		// TODO Auto-generated method stub
		
	}

}
