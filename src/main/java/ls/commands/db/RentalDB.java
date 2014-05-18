package ls.commands.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.Rental;
import ls.jdbc.DataBaseManager;

public class RentalDB {
	Statement stmt;
	ResultSet rs;
	DataBaseManager link;
	private HashMap<String, String> map;
//	select [property],[renter],[year],[cw],[status],[reserved_date],[confirmed_date]
	
	private ArrayList<Rental> resultSetToRentals() throws SQLException {
		ArrayList<Rental> list = new ArrayList<Rental>();
		while(rs.next()){
			String property, renter, status, reserved, confirmed;
			int year, cw;
			property = rs.getString("property");
			renter = rs.getString("renter");
			year = rs.getInt("year");
			cw = rs.getInt("cw");
			status = rs.getString("renter");
			reserved = rs.getString("reserved");
			confirmed = rs.getString("confirmed");
			list.add(new Rental(property, renter, year, cw, status, reserved, confirmed));
			
			
			
			
//			list.add(new Rental(username, pass, email, name));
		}
		return list;
	}

}
