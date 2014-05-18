package ls.commands.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.Property;
import ls.commands.User;
import ls.exception.ConnectionDatabaseException;
import ls.jdbc.DataBaseManager;

public class PropertiesDB {
	Statement stmt;
	static ResultSet rs;
	static DataBaseManager link;
	private static PreparedStatement prep;
	
	public static Property getPropertyByPid(HashMap<String, String> map)//TODO
	{
		return null;
	}
	
	private static Property resultSetToProperty() throws SQLException, ConnectionDatabaseException {
		String type, description, location, ownerUsername;
		int pid, price;
		pid = rs.getInt("pid");
		type = rs.getString("type");
		description = rs.getString("description");
		price = rs.getInt("price");
		location = rs.getString("location");
		ownerUsername = rs.getString("owner");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("username", ownerUsername);
		User owner = UsersDB.getUserByUsername(map);
		return new Property(pid, type, description, price, location, owner);
	}

	private static ArrayList<Property> resultSetToPropertyArrayList() throws SQLException, ConnectionDatabaseException {
		ArrayList<Property> list = new ArrayList<Property>();
		while(rs.next()){
			list.add(resultSetToProperty());
		}
		return list;
	}

}
