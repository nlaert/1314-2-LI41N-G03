package ls.commands.rentals;

import java.util.HashMap;

import ls.commands.CommandsUtils;
import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.commands.result.UserRentalsResult;
import ls.db.Rental;
import ls.db.RentalsDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class GetUsersRentals extends CommandsUtils implements ICommand<Rental> {

	
	@Override
	public ICommandResult<Rental> execute(HashMap<String, String> map) throws IllegalCommandException, ConnectionDatabaseException {
		return new UserRentalsResult( RentalsDB.GetUsersRentals(map));
	}
}
