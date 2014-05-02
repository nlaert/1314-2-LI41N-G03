package ls.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;

public class GetProperties extends CommandsUtils implements ICommand {

	Statement stmt;
	ResultSet rs;
	DataBaseManager link;

	@Override
	public ArrayList<String> execute(HashMap<String, String> map) throws IllegalCommandException, ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try {
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery("select [pid], [type], [description], [price], [location] from properties");
			list = resultSetToArrayList(rs);
			return list;
		} catch (SQLException e) {
			throw new ConnectionDatabaseException("Connection error",e);
		} 
		finally
		{
			close(rs,stmt,link);

		}
	}
}


