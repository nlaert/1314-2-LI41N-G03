package ls.commands.result;

import java.util.ArrayList;

import ls.db.Rental;

public class PropertiesRentalsResult implements ICommandResult<Rental>{

	private ArrayList<Rental> rentals;
	
	public PropertiesRentalsResult(ArrayList<Rental> rentals){
		this.rentals = rentals;
	}
	
	@Override
	public int getSize() {
		return rentals.size();
	}

	@Override
	public ArrayList<Rental> getArrayList() {
		return rentals;
	}
	

}
