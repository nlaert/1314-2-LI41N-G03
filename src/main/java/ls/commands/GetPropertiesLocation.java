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

public class GetPropertiesLocation implements ICommand{

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
			prep = link.getConnetion().prepareStatement("select [type], [description], [price], [location] from properties where " + key + " = ?");
			prep.setString(1,map.get(key));
			rs = prep.executeQuery();
			list = Utils.resultSetToArrayList(rs);
			return list;
		} catch (SQLException e) {
			throw new IllegalCommandException("Nao e possivel retornar a lista das propriedades");
		} finally
		{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionDatabaseException("Nao foi possivel fechar o ResultSet",e);
				}
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new ConnectionDatabaseException("Nao foi possivel fechar o statement",e);
				}
			if(link != null)
				link.closeConnection();
		
		}
	}

}
