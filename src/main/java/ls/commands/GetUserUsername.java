package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class GetUserUsername extends CloseCommands implements ICommand {

	Statement stmt;
	PreparedStatement prep;
	ResultSet rs;
	DataBaseManager link;
	private String key = "username";

	@Override
	public ArrayList<String> execute(HashMap<String, String> map) throws IllegalCommandException, ConnectionDatabaseException {
		ArrayList<String> result = new ArrayList<String>();
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select password, email, fullname from users where " + key  + " = ?");
			prep.setString(1, map.get(key));
			rs = prep.executeQuery();
			result = Utils.resultSetToArrayList(rs);
			return result;
		} catch (SQLException e) {
			System.out.println(e);
		} finally
		{
			close(rs, prep, link);

		}
		return result;	
	}
}




