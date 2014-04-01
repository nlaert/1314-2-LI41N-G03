package ls.commands;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import Exception.IllegalCommandException;
import Exception.ConnectionDatabaseException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class PostUsers implements iCommand {

	DataBaseManager link;
	PreparedStatement prep;
	@Override
	public ArrayList<String> execute(String command) throws IllegalCommandException,ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try{
			HashMap<String, String> map = Utils.mapper(command);
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
			System.out.println(e.getMessage());
		}finally{
			if(prep != null)
				try {
					prep.close();
				} catch (SQLException e) {
					throw new ConnectionDatabaseException("Nao foi possivel fechar o PreparedStatement",e);
				}
			if(link != null)
				link.closeConnection();
			}
		
		return list;
		
	}

}
