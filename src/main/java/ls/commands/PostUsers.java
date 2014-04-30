package ls.commands;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class PostUsers extends CloseCommands implements ICommand {

	DataBaseManager link;
	PreparedStatement prep;
	@Override
	public ArrayList<String> execute(HashMap<String, String> map) throws IllegalCommandException,ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try{
			
			link = new DataBaseManager();
			if (!Utils.checkAuth(map.get("auth_username"), map.get("auth_password"), link.getConnetion())){
				System.out.println("login invalido!");
				return null;		
			}
			prep = link.getConnetion().prepareStatement("insert into Users values (?, ?, ?, ?)");
			prep.setString(1, map.get("username"));
			prep.setString(2, map.get("password"));
			prep.setString(3, map.get("email"));
			prep.setString(4, map.get("fullname"));
			prep.executeUpdate();
			int count = prep.getUpdateCount();
			System.out.println(count + " row(s) affected");
		}catch (SQLException e){
			throw new IllegalCommandException("Nao foi possivel adicionar um cliente",e);
		}finally{
			close(prep,link);
			}
		
		return list;
		
	}

}
