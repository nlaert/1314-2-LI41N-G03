package ls.commands.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.Property;
import ls.commands.Rental;
import ls.commands.User;
import ls.exception.ConnectionDatabaseException;
import ls.jdbc.DataBaseManager;

public class RentalsDB {
	static Statement stmt;
	static ResultSet rs;
	static DataBaseManager link;
	
	
	
	private static ArrayList<Rental> resultSetToRentalArrayList() throws SQLException, ConnectionDatabaseException {
		ArrayList<Rental> list = new ArrayList<Rental>();
		while(rs.next()){
			list.add(resultSetToRental());
		}
		return list;
	}
	
	public static Rental resultSetToRental() throws SQLException, ConnectionDatabaseException{
		String status, reserved_date, confirmed_date;
		int year, cw;
		HashMap <String, String> map = new HashMap<String, String>();
		
		map.put("pid", rs.getString("property"));
		map.put("username", rs.getString("renter"));
		
		year = rs.getInt("year");
		cw = rs.getInt("cw");
		status = rs.getString("renter");
		reserved_date = rs.getString("reserved_date");
		confirmed_date = rs.getString("confirmed_date");
		
		Property property = PropertiesDB.getPropertyByPid(map);
		User renter = UsersDB.getUserByUsername(map);
		return new Rental(property, renter, year, cw, status, reserved_date, confirmed_date);
	}

}
