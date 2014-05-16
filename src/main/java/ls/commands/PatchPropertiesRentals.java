package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;

public class PatchPropertiesRentals extends CommandsUtils implements ICommand {

	DataBaseManager link;
	PreparedStatement prep;
	ResultSet rs;

	@Override
	public ArrayList<E> execute(HashMap<String, String> map)
			throws IllegalCommandException, ConnectionDatabaseException {
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
