package ls.commands;

public class Rental {
	
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
		return "property=" + property.pid + ", renter=" + renter.username
				+ ", year=" + year + ", cw=" + cw + ", status=" + status
				+ ", reserved_date=" + reserved_date + ", confirmed_date="
				+ confirmed_date;
	}

}
