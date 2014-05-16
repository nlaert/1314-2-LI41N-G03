package ls.commands.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ls.commands.CommandsUtils;
import ls.exception.ConnectionDatabaseException;
import ls.jdbc.DataBaseManager;

public class UsersDB extends CommandsUtils {
	Statement stmt;
	ResultSet rs;
	DataBaseManager link;
	private HashMap<String, String> map;
	
	public UsersDB(HashMap<String, String> map)
	{
		this.map = map;
	}
	
	public List<User> getAll()
	{
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery("select username, password, email, fullname from users");
			list = resultSetToArrayList(rs);
			
			return list;
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		} finally
		{
			close(rs, stmt, link);
		}
	}

}
