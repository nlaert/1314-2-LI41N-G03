package ls.commands;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;

public class PostUsers extends CommandsUtils implements ICommand {

	DataBaseManager link;
	PreparedStatement prep;
	@Override
	public ArrayList<String> execute(HashMap<String, String> map) throws IllegalCommandException,ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try{
			
			link = new DataBaseManager();
			if (!checkAuth(map.get("auth_username"), map.get("auth_password"), link.getConnetion())){
				throw new ConnectionDatabaseException("Invalid login");		
			}
			prep = link.getConnetion().prepareStatement("insert into Users values (?, ?, ?, ?)");
			prep.setString(1, map.get("username"));
			prep.setString(2, map.get("password"));
			prep.setString(3, map.get("email"));
			prep.setString(4, map.get("fullname"));
			prep.executeUpdate();
			int rows = prep.executeUpdate();
			list.add("Rows Updated");
			list.add(Integer.toString(rows));
		}catch (SQLException e){
			throw new ConnectionDatabaseException("Connection error",e);
		}finally{
			close(prep,link);
			}
		
		return list;
		
	}

}
