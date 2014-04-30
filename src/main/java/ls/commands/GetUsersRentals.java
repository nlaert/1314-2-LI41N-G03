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

public class GetUsersRentals extends CloseCommands implements ICommand {

	Statement stmt;
	PreparedStatement prep;
	ResultSet rs;
	DataBaseManager link;
	private String key = "renter";
	
	@Override
	public ArrayList<String> execute(HashMap<String, String> map)
			throws IllegalCommandException, ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try{
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [property], [renter], [year], [week], [status], "
					+ "[reserved_date], [confirmed_date] from rental where" + key + " = ?");
			prep.setString(1, map.get("username"));
			rs = prep.executeQuery();
			list = Utils.resultSetToArrayList(rs);
			return list;
		}catch (SQLException e){
			throw new IllegalCommandException("invalid command");
		}finally{
			close(rs, prep, link);
		}
	}
}