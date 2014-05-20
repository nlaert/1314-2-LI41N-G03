package ls.commands;

import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.db.RentalsDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class DeletePropertiesRental  implements ICommand<String> {

	@Override
	public ArrayList<String> execute(HashMap<String, String> map)
			throws IllegalCommandException, ConnectionDatabaseException {

		return RentalsDB.deletePropertiesRental(map);

	}
}
