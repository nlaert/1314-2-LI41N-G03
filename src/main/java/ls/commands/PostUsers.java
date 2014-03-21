package ls.commands;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import ls.jdbc.DataBaseManager;
import ls.propertiesRental.iCommand;
import ls.utils.Utils;

public class PostUsers implements iCommand {

	DataBaseManager link;
	@Override
	public void execute(String command) throws SQLException {
		link = new DataBaseManager();
		HashMap<String, String> map = Utils.mapper(command);
		if (!Utils.checkAuth(map.get("auth_username"), map.get("auth_password"), link.getConnetion())){
			System.out.println("login invalido!");
			return;		
		}
		
		PreparedStatement prep = link.getConnetion().prepareStatement("insert into Users values (?, ?, ?, ?)");
		prep. //TODO acabar insert
	}

}
