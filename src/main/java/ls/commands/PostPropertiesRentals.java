package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				System.out.println("login invalido!");
				return null;		
			}
		}
		
		return null;
	}
	
	
	

}
