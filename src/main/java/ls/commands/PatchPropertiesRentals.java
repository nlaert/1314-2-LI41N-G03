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

public class PatchPropertiesRentals extends CloseCommands implements ICommand {

	DataBaseManager link;
	PreparedStatement prep;
	ResultSet rs;

	@Override
	public ArrayList<String> execute(HashMap<String, String> map)
			throws IllegalCommandException, ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try {
			link = new DataBaseManager();
			if (!Utils.checkAuth(map.get("auth_username"), map.get("auth_password"), link.getConnetion()))
				throw new ConnectionDatabaseException("invalid username or password");
			if (!checkOwner(map.get("auth_username"), map.get("pid")))
				throw new ConnectionDatabaseException("You are not the owner of this property!");
			
			prep = link.getConnetion().prepareStatement("update rental set status = 'confirmed', confirmed_date = GETDATE() where property = ? and year = ? and cw = ?");
			prep.setString(1, map.get("pid"));
			prep.setString(2, map.get("year"));
			prep.setString(3, map.get("cw"));
			int rows = prep.executeUpdate();
			list.add("Rows Updated");
			list.add(Integer.toString(rows));
		} catch (SQLException e) {
			throw new IllegalCommandException("invalid command");
		} finally {
			close(rs, prep, link);
		}
		return list;
	}
	
	
	public boolean checkOwner(String owner, String pid) throws IllegalCommandException{
		try {
			prep = link.getConnetion().prepareStatement("select owner from properties where owner = ? and pid = ?");
			prep.setString(1, owner);
			prep.setString(2, pid);
			rs = prep.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new IllegalCommandException("Invalid Command");
		}
	}

}
