package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class GetPropertiesDetails implements iCommand {
	
	Statement stmt;
	PreparedStatement prep;
	ResultSet rs;
	DataBaseManager link;
	private String path = "GET /properties/details/{pid}";
	
	
	@Override
	public ArrayList<String> execute(String command) throws IllegalCommandException, ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		String [] pathParameters = Utils.pathParameters(path,command);
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [type], [description], [price], [location] from properties where pid = ?");
			prep.setString(1,pathParameters[0]);
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
