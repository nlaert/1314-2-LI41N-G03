package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;

public class PostProperties extends CommandsUtils implements ICommand {

	DataBaseManager link;
	PreparedStatement prep;
	ResultSet rs;
	@Override
	public ArrayList<E> execute(HashMap<String, String> map) throws IllegalCommandException, ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try{
			link = new DataBaseManager();
			if (!checkAuth(map.get("auth_username"), map.get("auth_password"), link.getConnetion())){
				throw new ConnectionDatabaseException("Invalid login");		
			}
			prep = link.getConnetion().prepareStatement("insert into properties values (?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, map.get("type"));
			prep.setString(2, map.get("description"));
			prep.setString(3, map.get("price"));
			prep.setString(4, map.get("location"));
			prep.setString(5, map.get("owner"));
			prep.executeUpdate();
			rs = prep.getGeneratedKeys();  
			list.add("pid");
			list = resultSetToArrayList(rs);
			return list;	
		}catch (SQLException e){
			throw new ConnectionDatabaseException("Connection error",e);
		}finally{
			close(rs, prep, link);
		}
	}
}