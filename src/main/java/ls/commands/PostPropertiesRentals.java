package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class PostPropertiesRentals extends CloseCommands implements ICommand {

	DataBaseManager link;
	PreparedStatement prep;
	ResultSet rs;
	@Override
	public ArrayList<String> execute(HashMap<String, String> map)
			throws IllegalCommandException, ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try{
			link = new DataBaseManager();
			if (!Utils.checkAuth(map.get("auth_username"), map.get("auth_password"), link.getConnetion()))
			{
				System.out.println("login invalido!");
				return null;		
			}
			prep = link.getConnetion().prepareStatement("insert into rental ([property],[renter],[year],[week],[status],[reserved_date]) values(?,?,?,?,?,?)");
			prep.setString(1, map.get("pid"));
			prep.setString(2, map.get("renter"));
			prep.setString(3, map.get("year"));
			prep.setString(4, map.get("week"));
			prep.setString(5, "pending");
			prep.setString(6, map.get("reserved_date"));
			prep.executeUpdate();
			int count = prep.getUpdateCount();
			System.out.println(count + " row(s) affected");
		} catch(SQLException e)
		{
			throw new IllegalCommandException("Nao foi possivel criar um aluguer", e);
		} finally
		{
			close(prep,link);
		}
		
		return list;
	}
	
	
	

}
