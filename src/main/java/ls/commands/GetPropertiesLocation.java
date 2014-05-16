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

public class GetPropertiesLocation extends CommandsUtils implements ICommand{

	Statement stmt;
	PreparedStatement prep;
	ResultSet rs;
	DataBaseManager link;
	
	@Override
	public ArrayList<E> execute(HashMap<String, String> map)
			throws ConnectionDatabaseException, IllegalCommandException, ConnectionDatabaseException {
		
		ArrayList<String> list = new ArrayList<String>();
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [pid], [type], [description], [price], [location] from properties where location = ?");
			prep.setString(1,map.get("location"));
			rs = prep.executeQuery();
			list = resultSetToArrayList(rs);
			return list;
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		} finally
		{
			close(rs,stmt,link);
		
		}
	}

}
