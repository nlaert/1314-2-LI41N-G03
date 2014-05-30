package ls.commands.rentals;

import java.util.HashMap;

import ls.commands.CommandsUtils;
import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.commands.result.PropertiesRentalsResult;
import ls.db.Rental;
import ls.db.RentalsDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class GetPropertiesRentals extends CommandsUtils implements ICommand<Rental> {

	
	@Override
	public ICommandResult<Rental> execute(HashMap<String, String> map)
			throws IllegalCommandException, ConnectionDatabaseException {
		return new PropertiesRentalsResult( RentalsDB.getPropertiesRentals(map));
		
	}

}
