package ls.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import ls.jdbc.DataBaseManager;
import ls.propertiesRental.iCommand;
import ls.utils.Utils;

public class GetProperties implements iCommand {
	
	Statement stmt;
	ResultSet rs;
	DataBaseManager link;
	
	private HashMap<String,iCommand> map;
	public GetProperties()
	{
		map = new HashMap<String,iCommand>();
		map.put("/details", new GetSelectProperties("pid"));
		map.put("/location", new GetSelectProperties("location"));
		map.put("/owner", new GetSelectProperties("owner"));
		map.put("/type", new GetSelectProperties("type"));
		
	}

	@Override
	public void execute(String command) throws CommandsException, SQLException {
		if(command.equals(""))
		{
			selectAllProperties();
			return;
		}
		String [] keyValue = Utils.limitator(command);
		if(map.containsKey(keyValue[0]))
		{
			keyValue[1] = keyValue[1].substring(1);
			map.get(keyValue[0]).execute(keyValue[1]);
		}
		else
			System.out.println("Informacao nao encontrada!");
		
	}

	private void selectAllProperties() throws SQLException {
		try {
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery("select [type], [description], [price], [location] from properties");
			System.out.println("Type \t Description \t Price \t\t\t\t Location ");
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


