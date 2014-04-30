package ls.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class GetProperties extends CloseCommands implements ICommand {

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
			list = Utils.resultSetToArrayList(rs);
			return list;
		} catch (SQLException e) {
			throw new IllegalCommandException("Nao foi possivel retornar a lista de todas as propriedades",e);
		} 
		finally
		{
			close(rs,stmt,link);

		}
	}
}


