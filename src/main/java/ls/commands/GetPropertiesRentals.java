package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;

public class GetPropertiesRentals extends CommandsUtils implements ICommand {

	PreparedStatement prep;
	ResultSet rs;
	DataBaseManager link;
	@Override
	public ArrayList<String> execute(HashMap<String, String> map)
			throws IllegalCommandException, ConnectionDatabaseException {

		ArrayList<String> list = new ArrayList<String>();
		try{
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [property],[renter],[year],[cw],[status],[reserved_date],[confirmed_date] "
					+ "from rental where [property] = ?");
			prep.setString(1, map.get("pid"));
			rs = prep.executeQuery();
			list = resultSetToArrayList(rs);
			return list;
		} catch(SQLException e)
		{
			throw new ConnectionDatabaseException("Connection error",e);
		} finally
		{
			close(rs,prep,link);
		}
	}

}