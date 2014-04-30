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

public class GetPropertiesLocation extends CloseCommands implements ICommand{

	Statement stmt;
	PreparedStatement prep;
	ResultSet rs;
	DataBaseManager link;
	private String key = "location";	
	
	@Override
	public ArrayList<String> execute(HashMap<String, String> map)
			throws ConnectionDatabaseException, IllegalCommandException, ConnectionDatabaseException {
		
		ArrayList<String> list = new ArrayList<String>();
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [pid], [type], [description], [price], [location] from properties where " + key + " = ?");
			prep.setString(1,map.get(key));
			rs = prep.executeQuery();
			list = Utils.resultSetToArrayList(rs);
			return list;
		} catch (SQLException e) {
			throw new IllegalCommandException("Nao e possivel retornar a lista das propriedades");
		} finally
		{
			close(rs,stmt,link);
		
		}
	}

}
