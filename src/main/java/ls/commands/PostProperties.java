package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import Exception.IllegalCommandException;
import Exception.ConnectionDatabaseException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class PostProperties implements iCommand {

	DataBaseManager link;
	PreparedStatement prep;
	ResultSet rs;
	@Override
	public ArrayList<String> execute(String command) throws IllegalCommandException, ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try{
			HashMap<String, String> map = Utils.mapper(command);
			link = new DataBaseManager();
			if (!Utils.checkAuth(map.get("auth_username"), map.get("auth_password"), link.getConnetion())){
				System.out.println("login invalido!");
				return null;		
			}
			prep = link.getConnetion().prepareStatement("insert into properties values (?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, map.get("type"));
			prep.setString(2, map.get("description"));
			prep.setString(3, map.get("price"));
			prep.setString(4, map.get("location"));
			prep.setString(5, map.get("owner"));
			prep.executeUpdate();
			int count = prep.getUpdateCount();
			System.out.println(count + " row(s) affected");
			rs = prep.getGeneratedKeys();  
			list = Utils.resultSetToArrayList(rs);
			return list;	
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}finally{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionDatabaseException("Não foi possivel fechar o ResultSet",e);
				}
			if(prep != null)
				try {
					prep.close();
				} catch (SQLException e) {
					throw new ConnectionDatabaseException("Não foi possivel fechar o Statement",e);
				}
			if(link != null)
				link.closeConnection();



		}
		return list;
	}
}