package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ls.jdbc.DataBaseManager;
import ls.propertiesRental.iCommand;

public class GetSelectProperties implements iCommand {
	
	Statement stmt;
	PreparedStatement prep;
	ResultSet rs;
	DataBaseManager link;
	
	private String source;
	public GetSelectProperties(String source)
	{
		this.source = source;
	}
	@Override
	public void execute(String command) throws SQLException {
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [type], [description], [price], [location] from properties where "+source+" = ?");
			if(source.equals("pid"))
				prep.setInt(1, Integer.parseInt(command));
			else
				prep.setString(1,command);
			
			rs = prep.executeQuery();
			System.out.println("Type \t\t\t Description \t\t\t\t Price \t\t\t Location ");
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

}
