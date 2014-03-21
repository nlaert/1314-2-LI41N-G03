package ls.project.get;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ls.jdbc.DataBaseManager;

public class GetUserUsername implements iGet {
	
	Statement stmt;
	PreparedStatement prep;
	ResultSet rs;
	DataBaseManager link;
	

	@Override
	public void executa(String command) {
		try {
			if(command.equals(""))
				selectWithoutUser();
			else
				selectWithUser(command);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private void selectWithoutUser() throws SQLException {
		try {
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery("select username, password, email, fullname from users");
			System.out.println("Username \t Password \t Email \t\t\t\t Fullname ");
			while(rs.next())
			{
				System.out.format("%s \t\t %s \t\t %s \t\t %s \n", rs.getString(1),rs.getString(2), 
						rs.getString(3), rs.getString(4));
			}
			System.out.println();
		} catch (SQLException e) {
			System.out.println(e);
		} finally
		{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(link != null)
				link.closeConnection();
			
		}
		
		
	}

	private void selectWithUser(String command) throws SQLException  {
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select username, password, email, fullname from users where username = ?");
			prep.setString(1, command);
			rs = prep.executeQuery();
			System.out.println("Username \t Password \t Email \t\t\t\t Fullname ");
			while(rs.next())
			{
				System.out.format("%s \t\t %s \t\t %s \t\t %s \n", rs.getString(1),rs.getString(2), 
						rs.getString(3), rs.getString(4));
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(link != null)
				link.closeConnection();
			
		}
		
		
	}

}
