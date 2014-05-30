package ls.commands.db;

import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.ICommand;
import ls.commands.Rental;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class GetPropertiesRentalsByYear implements ICommand<Rental> {

	@Override
	public ArrayList<Rental> execute(HashMap<String, String> map)
			throws IllegalCommandException, ConnectionDatabaseException {
		return RentalsDB.getPropertiesRentalsByYear(map);
	}

}
