package ls.commands.properties;

import java.util.HashMap;

import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.commands.result.PropertyDetailsResult;
import ls.db.PropertiesDB;
import ls.db.Property;
import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;

public class GetPropertiesDetails implements ICommand<Property> {
	

	
	@Override
	public ICommandResult<Property> execute(HashMap<String, String> map) throws IllegalCommandException, ConnectionDatabaseException, FileException {
		return new PropertyDetailsResult (PropertiesDB.getPropertiesDetails(map));
	}

}
