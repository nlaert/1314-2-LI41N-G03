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

public class GetUserUsername implements iCommand {

	Statement stmt;
	PreparedStatement prep;
	ResultSet rs;
	DataBaseManager link;


	@Override
	public ArrayList<String> execute(String command) throws IllegalCommandException, ConnectionDatabaseException {
		command = Utils.limitatorSpecificCommand(command);
		ArrayList<String> list = new ArrayList<String>();
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select username, password, email, fullname from users where username = ?");
			prep.setString(1, command);
			rs = prep.executeQuery();
			list = Utils.resultSetToArrayList(rs);
			return list;
		} catch (SQLException e) {
			System.out.println(e);
		} finally
		{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new ConnectionDatabaseException("Impossivel fechar o ResutSet", e);
				}
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(link != null)
				link.closeConnection();

		}
		return list;	
	}
}




