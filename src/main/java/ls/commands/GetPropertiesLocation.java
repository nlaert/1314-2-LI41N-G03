package ls.commands;

import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.db.PropertiesDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class GetPropertiesLocation  implements ICommand<Property>{


	
	@Override
	public ArrayList<Property> execute(HashMap<String, String> map)
			throws ConnectionDatabaseException, IllegalCommandException, ConnectionDatabaseException {
		return PropertiesDB.getPropertiesLocation(map);
		
	}

}
