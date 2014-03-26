package ls.commands;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import Exception.ClosingDataAccessException;
import Exception.CommandsException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class PostUsers implements iCommand {

	DataBaseManager link;
	PreparedStatement prep;
	@Override
	public void execute(String command) throws CommandsException, ClosingDataAccessException {
		try{
			link = new DataBaseManager();
			HashMap<String, String> map = Utils.mapper(command);
			if (!Utils.checkAuth(map.get("auth_username"), map.get("auth_password"), link.getConnetion())){
				System.out.println("login invalido!");
				return;		
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
			System.out.println(e.getMessage());
		}finally{
			if(prep != null)
				try {
					prep.close();
				} catch (SQLException e) {
					throw new ClosingDataAccessException("Nao foi possivel fechar o PreparedStatement",e);
				}
			if(link != null)
				link.closeConnection();


		}
		
		
		
	}

}
