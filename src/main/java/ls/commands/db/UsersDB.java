package ls.commands.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ls.commands.CommandsUtils;
import ls.commands.User;
import ls.exception.ConnectionDatabaseException;
import ls.jdbc.DataBaseManager;

public class UsersDB extends CommandsUtils {
	Statement stmt;
	ResultSet rs;
	DataBaseManager link;
	private PreparedStatement prep;
	
	public List<User> getAll() throws ConnectionDatabaseException
	{
		ArrayList<User> list = new ArrayList<User>();
		
		try {
			
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery("select username, password, email, fullname from users");
			list = resultSetToUserArrayList();
			
			return list;
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		} finally
		{
			close(rs, stmt, link);
		}
	}
	
	public User getUserByUsername(HashMap<String, String> map) throws ConnectionDatabaseException
	{
	try {
			
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			prep = link.getConnetion().prepareStatement("select password, email, fullname from users where username = ?");
			prep.setString(1, map.get("username"));
			rs = prep.executeQuery();
			if (rs.next())
				return resultSetToUser();
			else
				return null;
//				throw new IllegalCommandException("Parameters not found");
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		} finally
		{
			close(rs, stmt, link);
		}
	}

	private User resultSetToUser() throws SQLException {
		String username, pass, email, name;
		username = rs.getString("username");
		pass = rs.getString("password");
		email = rs.getString("email");
		name = rs.getString("fullname");
		return new User(username, pass, email, name);
	}

	private ArrayList<User> resultSetToUserArrayList() throws SQLException {
		ArrayList<User> list = new ArrayList<User>();
		while(rs.next()){
			list.add(resultSetToUser());
		}
		return list;
	}

}
