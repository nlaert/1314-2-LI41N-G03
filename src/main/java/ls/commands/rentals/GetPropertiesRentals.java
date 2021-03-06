package ls.commands.rentals;

import java.util.HashMap;

import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.commands.result.PropertiesRentalsResult;
import ls.db.Rental;
import ls.db.RentalsDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;

public class GetPropertiesRentals implements ICommand<Rental> {

	
	@Override
	public ICommandResult<Rental> execute(HashMap<String, String> map)
			throws IllegalCommandException, ConnectionDatabaseException, FileException {
		return new PropertiesRentalsResult( RentalsDB.getPropertiesRentals(map));
		
	}

}
