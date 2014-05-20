package ls.commands;

import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.db.RentalsDB;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;

public class GetPropertiesRentalsWithDate  implements ICommand<Rental>{
	
	
	@Override
	public ArrayList<Rental> execute(HashMap<String, String> map) throws IllegalCommandException, ConnectionDatabaseException {
		return RentalsDB.getPropertiesRentalsWithDate(map);
		
		
	
	}

}
