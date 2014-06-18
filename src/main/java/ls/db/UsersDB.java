package ls.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.CommandsUtils;
import ls.exception.AuthenticationException;
import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;

public class UsersDB extends CommandsUtils {
	static Statement stmt;
	static ResultSet rs;
	static DataBaseManager link;
	private static PreparedStatement prep;
	
	public static ArrayList<User> getAll() throws ConnectionDatabaseException, IllegalCommandException, FileException
	{
		try {
			
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery("select username, password, email, fullname from users");
			return resultSetToUserArrayList();
			
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		} finally
		{
			close(rs, stmt, link);
		}
	}
	
	public static User getUser(HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException, FileException
	{
		return getUserByUsername(map).get(0);
	}
	
	public static ArrayList<User> getUserByUsername(HashMap<String, String> map) throws ConnectionDatabaseException, IllegalCommandException, FileException
	{
		try {
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			prep = link.getConnetion().prepareStatement("select username, password, email, fullname from users where username = ?");
			prep.setString(1, map.get("username"));
			rs = prep.executeQuery();
			return resultSetToUserArrayList();
			
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		} finally
		{
			close(rs, stmt, link);
		}
	}
	
	public static ArrayList<String> PostUser(HashMap<String, String> map) throws ConnectionDatabaseException, AuthenticationException, IllegalCommandException, FileException
	{
		ArrayList<String> list = new ArrayList<String>();
		try{
			
			link = new DataBaseManager();
			if (!checkAuth(map.get("auth_username"), map.get("auth_password"), link.getConnetion())){
				throw new AuthenticationException("Invalid login");		
			}
			
			if(getUserByUsername(map).size()== 1)
				throw new IllegalCommandException("Username already exists");
				
			prep = link.getConnetion().prepareStatement("insert into Users values (?, ?, ?, ?)");
			prep.setString(1, map.get("username"));
			prep.setString(2, map.get("password"));
			prep.setString(3, map.get("email"));
			prep.setString(4, map.get("fullname"));
			int rows = prep.executeUpdate();
			list.add("Rows Updated");
			list.add(Integer.toString(rows));
		}catch (SQLException e){
			throw new ConnectionDatabaseException("Connection error",e);
		}finally{
			close(prep,link);
			}
		
		return list;
		
	}
	
		private static User resultSetToUser() throws SQLException {
		String username, pass, email, name;
		username = rs.getString("username");
		pass = rs.getString("password");
		email = rs.getString("email");
		name = rs.getString("fullname");
		return new User(username, pass, email, name);
	}

	private static ArrayList<User> resultSetToUserArrayList() throws SQLException, IllegalCommandException {
	
		ArrayList<User> list = new ArrayList<User>();
		while(rs.next()){
			list.add(resultSetToUser());
		}
		return list;
	}

}
