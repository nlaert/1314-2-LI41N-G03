package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Exception.CloseConnectionException;
import Exception.CommandsException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class GetUserUsername implements iCommand {

	Statement stmt;
	PreparedStatement prep;
	ResultSet rs;
	DataBaseManager link;


	@Override
	public ArrayList<String> execute(String command) throws CommandsException, CloseConnectionException {
		command = Utils.limitatorSpecificCommand(command);
		ArrayList<String> list = new ArrayList<String>();
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select username, password, email, fullname from users where username = ?");
			prep.setString(1, command);
			rs = prep.executeQuery();
			list = Utils.resultSetToArrayList(rs);
			//			System.out.println("Username \t Password \t Email \t\t\t\t Fullname ");
			//			while(rs.next())
			//			{
			//				System.out.format("%s \t\t %s \t\t %s \t\t %s \n", rs.getString(1),rs.getString(2), 
			//						rs.getString(3), rs.getString(4));
			//			}
			//			System.out.println();
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
					e.printStackTrace();
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




