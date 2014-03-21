package ls.commands;

import java.sql.SQLException;
import java.util.HashMap;

import ls.propertiesRental.iCommand;
import ls.utils.Utils;

public class GetDetails implements iCommand {
	private HashMap<String,iCommand> map;
	public GetDetails()
	{
		map = new HashMap<String,iCommand>();
		
	}

	@Override
	public void execute(String command) throws SQLException {
		if(command.equals(""))
		{
			System.out.println("Informacao nao encontrada!");
			return;
		}
		String [] keyValue = Utils.limitator(command);
		if(map.containsKey(keyValue[0]))
		{
			map.get(keyValue[0]).execute(keyValue[1]);
		}
		else
		{
			keyValue[0] = keyValue[0].substring(1);
			getDetails(keyValue[0]);

		}
		

	}

	private void getDetails(String string) {
		// TODO Auto-generated method stub
		
	}
		


}
