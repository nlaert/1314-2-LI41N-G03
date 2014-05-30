package ls.db;


public class Rental implements IType {
	
	public Property property;
	public User renter;
	public int year;
	public int cw;
	public String status;
	public String reserved_date;
	public String confirmed_date;
	
	public Rental(Property property, User renter, int year, int cw,
			String status, String reserved_date, String confirmed_date) {
		this.property = property;
		this.renter = renter;
		this.year = year;
		this.cw = cw;
		this.status = status;
		this.reserved_date = reserved_date;
		this.confirmed_date = confirmed_date;
	}

	@Override
	public String toString() {
		return property.pid + "\t" + renter.username
				+ "\t" + year + "\t" + cw + "\t" + status
				+ "\t" + reserved_date + "\t"
				+ confirmed_date;
	}

	@Override
	public String[] getColumNames() {
		return new String[] {"pid", "renter", "year", "cw", "status", "reserved date", "confirmed date"};
	}

}
