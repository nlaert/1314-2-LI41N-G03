package ls.commands.properties;

import java.util.HashMap;

import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.commands.result.PropertiesResult;
import ls.db.PropertiesDB;
import ls.db.Property;
import ls.exception.AuthenticationException;
import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;

public class PostProperties  implements ICommand<Property> {

	
	@Override
	public ICommandResult<Property> execute(HashMap<String, String> map) throws IllegalCommandException, ConnectionDatabaseException, AuthenticationException, FileException {
		return new PropertiesResult( PropertiesDB.postProperties(map));
	}
}