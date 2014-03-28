package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ls.jdbc.DataBaseManager;
import ls.utils.Utils;
import Exception.IllegalCommandException;
import Exception.ConnectionDatabaseException;

public class GetPropertiesLocation implements iCommand{

	Statement stmt;
	PreparedStatement prep;
	ResultSet rs;
	DataBaseManager link;
	@Override
	public ArrayList<String> execute(String command)
			throws ConnectionDatabaseException, IllegalCommandException, ConnectionDatabaseException {
		command = Utils.limitatorSpecificCommand(command);
		ArrayList<String> list = new ArrayList<String>();
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [type], [description], [price], [location] from properties where location = ?");
			prep.setString(1,command);
			rs = prep.executeQuery();
			list = Utils.resultSetToArrayList(rs);
			return list;
		} catch (SQLException e) {
			throw new IllegalCommandException("Não é possivel retornar a lista das propriedades");
		} finally
		{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionDatabaseException("Não foi possivel fechar o ResultSet",e);
				}
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new ConnectionDatabaseException("Não foi possivel fechar o statement",e);
				}
			if(link != null)
				link.closeConnection();
		
		}
	}

}
