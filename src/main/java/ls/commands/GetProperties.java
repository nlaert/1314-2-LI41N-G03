package ls.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class GetProperties implements iCommand {

	Statement stmt;
	ResultSet rs;
	DataBaseManager link;

	@Override
	public ArrayList<String> execute(String command) throws IllegalCommandException, ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try {
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery("select [type], [description], [price], [location] from properties");
			list = Utils.resultSetToArrayList(rs);
			return list;
		} catch (SQLException e) {
			throw new IllegalCommandException("Não foi possivel retornar a lista de todas as propriedades",e);
		} 
		finally
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
					throw new ConnectionDatabaseException("Não foi possivel fechar o Statement",e);
				}
			if(link != null)
				link.closeConnection();

		}
	}
}


