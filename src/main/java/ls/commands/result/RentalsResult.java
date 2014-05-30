package ls.commands.result;

import java.util.ArrayList;

import ls.db.Rental;


public class RentalsResult implements ICommandResult<Rental> {

	private ArrayList<Rental> rentals;
	
	
	public RentalsResult(ArrayList<Rental> rentals)
	{
		this.rentals =  rentals;
	}
	
	public ArrayList<Rental> getRentals()
	{
		return rentals;
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
