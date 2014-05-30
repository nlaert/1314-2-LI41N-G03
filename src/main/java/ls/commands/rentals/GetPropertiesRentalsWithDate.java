package ls.commands.rentals;

import java.util.HashMap;

import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.commands.result.PropertiesRentalsWithDateResult;
import ls.db.Rental;
import ls.db.RentalsDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class GetPropertiesRentalsWithDate  implements ICommand<Rental>{
	
	
	@Override
	public ICommandResult<Rental> execute(HashMap<String, String> map) throws IllegalCommandException, ConnectionDatabaseException {
		return new PropertiesRentalsWithDateResult(RentalsDB.getPropertiesRentalsWithDate(map));
		
		
	
	}

}
