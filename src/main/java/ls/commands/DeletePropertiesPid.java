package ls.commands;

import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.db.PropertiesDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class DeletePropertiesPid implements ICommand<String> {

	@Override
	public ArrayList<String> execute(HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException{
			
		return PropertiesDB.deletePropertiesByPid(map);
	}
	
	

}
