package ls.commands.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.CommandsUtils;
import ls.commands.Property;
import ls.commands.User;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;

public class PropertiesDB  extends CommandsUtils  {
	static Statement stmt;
	static ResultSet rs;
	static DataBaseManager link;
	private static PreparedStatement prep;
	
	public static Property getPropertyByPid(HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException
	{
		return getPropertiesDetails(map).get(0);
	}
	public static ArrayList<Property> getProperties() throws ConnectionDatabaseException, IllegalCommandException
	{
		ArrayList<Property> list = new ArrayList<Property>();
		try {
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery("select [pid], [type], [description], [price], [location], [owner] from properties");
			list = resultSetToPropertyArrayList();
			return list;
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		} 
		finally
		{
			close(rs,stmt,link);

		}
	}
	private static Property resultSetToProperty() throws SQLException, ConnectionDatabaseException, IllegalCommandException {
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
		User owner = UsersDB.getUser(map);
		return new Property(pid, type, description, price, location, owner);
	}

	private static ArrayList<Property> resultSetToPropertyArrayList() throws SQLException, ConnectionDatabaseException, IllegalCommandException {
		ArrayList<Property> list = new ArrayList<Property>();
		while(rs.next()){
			list.add(resultSetToProperty());
		}
		return list;
	}
	public static ArrayList<Property> getPropertiesDetails(HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException {
		
		ArrayList<Property> list = new ArrayList<Property>();
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [pid], [type], [description], [price], [location], [owner] from properties where pid  = ?");
			prep.setString(1,map.get("pid"));
			rs = prep.executeQuery();
			list = resultSetToPropertyArrayList();
			return list;
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		} finally
		{
			close(rs, prep, link);
		
		}
	}
	
	public static ArrayList<Property> getPropertiesLocation(HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException {
		ArrayList<Property> list = new ArrayList<Property>();
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [pid], [type], [description], [price], [location], [owner] from properties where location = ?");
			prep.setString(1,map.get("location"));
			rs = prep.executeQuery();
			list = resultSetToPropertyArrayList();
			return list;
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		} finally
		{
			close(rs,stmt,link);
		
		}
	}
	public static ArrayList<Property> getPropertiesOwner(
			HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException {
		ArrayList<Property> list = new ArrayList<Property>();
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [pid], [type], [description], [price], [location], [owner] from properties where owner = ?");
			if (map.containsKey("owner"))
				prep.setString(1,map.get("owner"));
			else if (map.containsKey("username"))
				prep.setString(1,map.get("username"));
			rs = prep.executeQuery();
			list = resultSetToPropertyArrayList();
			return list;
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		} finally
		{
			close(rs,prep,link);
		}
	}
	public static ArrayList<Property> getPropertiesType(
			HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException {
		ArrayList<Property> list = new ArrayList<Property>();
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [pid], [type], [description], [price], [location], [owner] from properties where [type] = ?");
			prep.setString(1,map.get("type"));			
			rs = prep.executeQuery();
			list = resultSetToPropertyArrayList();
			return list;
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		} finally
		{
			close(rs, prep, link);
		
		}
	}
	public static ArrayList<String> postProperties(HashMap<String, String> map) throws ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try{
			link = new DataBaseManager();
			if (!checkAuth(map.get("auth_username"), map.get("auth_password"), link.getConnetion())){
				throw new ConnectionDatabaseException("Invalid login");		
			}
			prep = link.getConnetion().prepareStatement("insert into properties values (?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, map.get("type"));
			prep.setString(2, map.get("description"));
			prep.setString(3, map.get("price"));
			prep.setString(4, map.get("location"));
			prep.setString(5, map.get("owner"));
			prep.executeUpdate();
			rs = prep.getGeneratedKeys();  
			if(rs.next())
			{
				list.add("pid");
				list.add(Integer.toString(rs.getInt(1))); //duvida
			}
		
			return list;	
		}catch (SQLException e){
			throw new ConnectionDatabaseException("Connection error",e);
		}finally{
			close(rs, prep, link);
		}
	}
	
	public static ArrayList<String >deletePropertiesByPid(HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException{
		Connection conn = null;
		ArrayList<String> list = new ArrayList<String>();
		try{
			link = new DataBaseManager();
			conn = link.getConnetion();
			if (!checkAuth(map.get("auth_username"), map.get("auth_password"), conn))
			{
				throw new ConnectionDatabaseException("Invalid login");	
			}
			String checkOwner =  "select pid from properties where pid = ? and owner = ?";
			if (!checkIfExists(checkOwner, new String [] {map.get("pid"), map.get("auth_username")}, conn))
			{
				throw new ConnectionDatabaseException("You are not the owner");
			}
			conn.setAutoCommit(false);
			prep = conn.prepareStatement("delete from rental where property=?");
			prep.setString(1, map.get("pid"));
			int rows = prep.executeUpdate();
			
			prep = conn.prepareStatement("delete from properties where pid = ?");
			prep.setString(1, map.get("pid"));
			rows += prep.executeUpdate();
			conn.commit();
			list.add("Rows Updated");
			list.add(Integer.toString(rows));
			return list;
		} catch(SQLException e)
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ConnectionDatabaseException("It is not possible to Delete", e1);
			}
			throw new ConnectionDatabaseException("it is not possible to Delete", e);
		} 
		finally
		{
			close(prep,link);
		}
	}
	
	

}
