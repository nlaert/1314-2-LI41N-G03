package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class PostPropertiesRentals extends CloseCommands implements ICommand {

	DataBaseManager link;
	PreparedStatement prep;
	ResultSet rs;
	@Override
	public ArrayList<String> execute(HashMap<String, String> map)
			throws IllegalCommandException, ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try{
			link = new DataBaseManager();
			if (!Utils.checkAuth(map.get("auth_username"), map.get("auth_password"), link.getConnetion()))
			{
				throw new ConnectionDatabaseException("Invalid login");	
			}
			if(Utils.checkBDD("select [year],[cw] from rental where [year] = ? and [cw] = ?",new String[] {map.get("year"),map.get("cw")}, link.getConnetion()))
			{
				throw new IllegalCommandException("a rent for the chosen date already exists");
			}
			prep = link.getConnetion().prepareStatement("insert into rental ([property],[renter],[year],[cw],[status],[reserved_date]) values(?,?,?,?,?,getDate())");
			prep.setString(1, map.get("pid"));
			prep.setString(2, map.get("auth_username"));
			prep.setString(3, map.get("year"));
			prep.setString(4, map.get("cw"));
			prep.setString(5, "pending");
			int count = prep.executeUpdate();
			if(count >0)
			{
				prep = link.getConnetion().prepareStatement("select [property],[renter],[year],[cw],[status],[reserved_date],[confirmed_date] from rental where [year] = ? and [cw] = ?");
				prep.setString(1, map.get("year"));
				prep.setString(2, map.get("cw"));
				rs = prep.executeQuery();
				list = Utils.resultSetToArrayList(rs);
			}
			return list;
		} catch(SQLException e)
		{
			throw new IllegalCommandException("Nao foi possivel criar um aluguer", e);
		} 
		finally
		{
			close(prep,link);
		}
		
		
	}
	
	
	

}
