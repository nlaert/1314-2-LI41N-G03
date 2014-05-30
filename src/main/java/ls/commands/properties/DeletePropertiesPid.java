package ls.commands.properties;

import java.util.HashMap;

import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.commands.result.StringResult;
import ls.db.PropertiesDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class DeletePropertiesPid implements ICommand<String> {

	@Override
	public ICommandResult<String> execute(HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException{
			
		return new StringResult(PropertiesDB.deletePropertiesByPid(map));
	}
	
	

}
