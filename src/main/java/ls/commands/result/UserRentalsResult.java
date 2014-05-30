package ls.commands.result;

import java.util.ArrayList;

import ls.db.Rental;

public class UserRentalsResult implements ICommandResult<Rental> {

	
	private ArrayList<Rental> rentals;

	public UserRentalsResult(ArrayList<Rental> rentals)
	{
		this.rentals = rentals;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Rental> getArrayList() {
		return rentals;
	}
	
	public ArrayList<Rental> getRentals()
	{
		return rentals;
	}
	

}
