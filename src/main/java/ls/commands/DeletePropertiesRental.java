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

public class DeletePropertiesRental extends CloseCommands implements ICommand {

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
			if(!checkPending(map))
			{
				throw new IllegalCommandException("a rent for the chosen date as confirmed or not exists");
			}
			prep = link.getConnetion().prepareStatement("delete from rental where [property] = ? and [year] = ? and [cw] = ?");
			prep.setString(1, map.get("pid"));
			prep.setString(2, map.get("year"));
			prep.setString(3, map.get("cw"));
			int rows = prep.executeUpdate();
			list.add("Rows Updated");
			list.add(Integer.toString(rows));

			return list;
		} catch(SQLException e)
		{
			throw new IllegalCommandException("Nao foi possivel fazer o delete", e);
		} 
		finally
		{
			close(prep,link);
		}


	}
	private boolean checkPending(HashMap<String, String> map) throws IllegalCommandException {
		String currentStatus = "";

		try{
			prep = link.getConnetion().prepareStatement("select [status] from rental where [property] = ? and [year] = ? and [cw] = ?");
			prep.setString(1,map.get("pid"));
			prep.setString(2,map.get("year"));
			prep.setString(3,map.get("cw"));
			rs = prep.executeQuery();
			if(rs.next())
			{
				currentStatus = rs.getString(1);
			}
			if(currentStatus.equals("pending"))
				return true;
			else
				return false;
		}catch(SQLException e)
		{
			throw new IllegalCommandException("Illegal command",e);
		}

	}
}
