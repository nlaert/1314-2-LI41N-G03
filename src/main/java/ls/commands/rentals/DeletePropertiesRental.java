package ls.commands.rentals;

import java.util.HashMap;

import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.commands.result.StringResult;
import ls.db.RentalsDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;

public class DeletePropertiesRental  implements ICommand<String> {

	@Override
	public ICommandResult<String> execute(HashMap<String, String> map)
			throws IllegalCommandException, ConnectionDatabaseException, FileException {

		return new StringResult(RentalsDB.deletePropertiesRental(map));

	}
}
