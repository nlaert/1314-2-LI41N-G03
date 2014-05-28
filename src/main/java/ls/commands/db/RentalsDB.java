package ls.commands.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.CommandsUtils;
import ls.commands.Property;
import ls.commands.Rental;
import ls.commands.User;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;

public class RentalsDB extends CommandsUtils{
	static Statement stmt;
	static ResultSet rs;
	static DataBaseManager link;
	static PreparedStatement prep;
	
	
	
	private static ArrayList<Rental> resultSetToRentalArrayList() throws SQLException, ConnectionDatabaseException, IllegalCommandException {
		ArrayList<Rental> list = new ArrayList<Rental>();
		while(rs.next()){
			list.add(resultSetToRental());
		}
		return list;
	}
	
	public static Rental resultSetToRental() throws SQLException, ConnectionDatabaseException, IllegalCommandException{
		String status, reserved_date, confirmed_date;
		int year, cw;
		HashMap <String, String> map = new HashMap<String, String>();
		
		map.put("pid", rs.getString("property"));
		map.put("username", rs.getString("renter"));
		
		year = rs.getInt("year");
		cw = rs.getInt("cw");
		status = rs.getString("status");
		reserved_date = rs.getString("reserved_date");
		confirmed_date = rs.getString("confirmed_date");
		
		Property property = PropertiesDB.getPropertyByPid(map);
		User renter = UsersDB.getUser(map);
		return new Rental(property, renter, year, cw, status, reserved_date, confirmed_date);
	}

	public static ArrayList<String> deletePropertiesRental(HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException{
		ArrayList<String> list = new ArrayList<String>();
		try{
			link = new DataBaseManager();
			if (!checkAuth(map.get("auth_username"), map.get("auth_password"), link.getConnetion()))
			{
				throw new ConnectionDatabaseException("Invalid login");	
			}
			if(!checkPending(map))
			{
				throw new IllegalCommandException("a rent for the chosen date as confirmed or not exists");
			}
			prep = link.getConnetion().prepareStatement("delete from rental where [property] = ? and [year] = ? and [cw] = ?");
			prep.setString(1, map.get("pid"));
			prep.setString(2, map.get("year"));
			prep.setString(3, map.get("cw"));
			int rows = prep.executeUpdate();
			list.add("Rows Updated");
			list.add(Integer.toString(rows));
			return list;
		} catch(SQLException e)
		{
			throw new IllegalCommandException("it is not possible to Delete", e);
		} 
		finally
		{
			close(prep,link);
		}
	}
	private static boolean checkPending(HashMap<String, String> map) throws IllegalCommandException {
		String currentStatus = "";

		try{
			prep = link.getConnetion().prepareStatement("select [status] from rental where [property] = ? and [year] = ? and [cw] = ?");
			prep.setString(1,map.get("pid"));
			prep.setString(2,map.get("year"));
			prep.setString(3,map.get("cw"));
			rs = prep.executeQuery();
			if(rs.next())
			{
				currentStatus = rs.getString(1);
			}
			if(currentStatus.equals("pending"))
				return true;
			else
				return false;
		}catch(SQLException e)
		{
			throw new IllegalCommandException("Illegal command");
		}
	}

	public static ArrayList<Rental> getPropertiesRentals(HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException {
		ArrayList<Rental> list = new ArrayList<Rental>();
		try{
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [property],[renter],[year],[cw],[status],[reserved_date],[confirmed_date] "
					+ "from rental where [property] = ?");
			prep.setString(1, map.get("pid"));
			rs = prep.executeQuery();
			list = resultSetToRentalArrayList();
			return list;
		} catch(SQLException e)
		{
			throw new ConnectionDatabaseException("Connection error",e);
		} finally
		{
			close(rs,prep,link);
		}
	}

	public static ArrayList<Rental> getPropertiesRentalsWithDate(HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException {
		ArrayList<Rental> list = new ArrayList<Rental>();
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [property], [renter], [year], [cw], [status], "
					+ "[reserved_date], [confirmed_date] from rental where [property] = ? and [year] = ? and [cw] = ?");

			prep.setString(1,map.get("pid"));
			prep.setString(2,map.get("year"));
			prep.setString(3,map.get("cw"));
			rs = prep.executeQuery();
			list = resultSetToRentalArrayList();
			return list;
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		} finally
		{
			close(rs, prep, link);
		
		}
	}

	public static ArrayList<Rental> postPropertiesRentals(
			HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException {
		ArrayList<Rental> list = new ArrayList<Rental>();
		try{
			link = new DataBaseManager();
			if (!checkAuth(map.get("auth_username"), map.get("auth_password"), link.getConnetion()))
			{
				throw new ConnectionDatabaseException("Invalid login");	
			}
			if(checkIfExists("select [year],[cw] from rental where [year] = ? and [cw] = ? and [property] = ?",new String[] {map.get("year"),map.get("cw"),map.get("pid")}, link.getConnetion()))
			{
				throw new IllegalCommandException("a rent for the chosen date already exists");
			}
			prep = link.getConnetion().prepareStatement("insert into rental ([property],[renter],[year],[cw],[status],[reserved_date]) values(?,?,?,?,?,getDate())");
			prep.setString(1, map.get("pid"));
			prep.setString(2, map.get("auth_username"));
			prep.setString(3, map.get("year"));
			prep.setString(4, map.get("cw"));
			prep.setString(5, "pending");
			int count = prep.executeUpdate();
			if(count >0)
			{
				prep = link.getConnetion().prepareStatement("select [property],[renter],[year],[cw],[status],[reserved_date],[confirmed_date] from rental where [year] = ? and [cw] = ?");
				prep.setString(1, map.get("year"));
				prep.setString(2, map.get("cw"));
				rs = prep.executeQuery();
				list = resultSetToRentalArrayList();
			}
			return list;
		} catch(SQLException e)
		{
			throw new ConnectionDatabaseException("Connection error",e);
		} 
		finally
		{
			close(prep,link);
		}
	}

	public static ArrayList<String> patchPropertiesRentals(
			HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException {
		ArrayList<String> list = new ArrayList<String>();
		try {
			link = new DataBaseManager();
			if (!checkIfExists("Select us.[username], prop.[owner] from Users as us "
					+ "inner join [properties] as prop on(prop.[owner] = us.username) "
					+ "where us.[username] = ? and us.[password] = ? and prop.[pid] = ?", 
					new String[] {map.get("auth_username"),map.get("auth_password"),map.get("pid")}, link.getConnetion()))
			{
				throw new ConnectionDatabaseException("You are not the owner of this property or invalid username/password!");
			}

			prep = link.getConnetion().prepareStatement("update rental set status = 'confirmed', "
					+ "confirmed_date = GETDATE() where property = ? and year = ? and cw = ?");

			prep.setString(1, map.get("pid"));
			prep.setString(2, map.get("year"));
			prep.setString(3, map.get("cw"));
			int rows = prep.executeUpdate();
			list.add("Rows Updated");
			list.add(Integer.toString(rows));
		} catch (SQLException e) {
			throw new IllegalCommandException("invalid command",e);
		} finally {
			close(rs, prep, link);
		}
		return list;
	}



}
