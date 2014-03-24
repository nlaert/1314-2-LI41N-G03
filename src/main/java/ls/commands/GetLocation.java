package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import ls.jdbc.DataBaseManager;
import ls.propertiesRental.iCommand;
import ls.utils.Utils;

public class GetLocation implements iCommand {
	
	Statement stmt;
	PreparedStatement prep;
	ResultSet rs;
	DataBaseManager link;
	
	private HashMap<String,iCommand> map;
	public GetLocation()
	{
		map = new HashMap<String,iCommand>();
	}
	
	

	@Override
	public void execute(String command) throws CommandsException, SQLException {
		
		if(command.equals(""))
		{
			System.out.println("Informacao nao encontrada!");
			return;
		}
		String [] keyValue = Utils.limitator(command);
		if(map.containsKey(keyValue[0]))
		{
			map.get(keyValue[0]).execute(keyValue[1]);
		}
		else
		{
			keyValue[0] = keyValue[0].substring(1);
			getLocation(keyValue[0]);

		}
		

	}
	private void getLocation(String command) throws SQLException  {
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [type], [description], [price], [location] from properties where location = ?");
			prep.setString(1, command);
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
