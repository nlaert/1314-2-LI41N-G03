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
import ls.utils.Utils;

public class PostProperties implements ICommand {

	DataBaseManager link;
	PreparedStatement prep;
	ResultSet rs;
	@Override
	public ArrayList<String> execute(HashMap<String, String> map) throws IllegalCommandException, ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try{
			HashMap<String, String> parametersMap = Utils.mapper(command[2],map);
			link = new DataBaseManager();
			if (!Utils.checkAuth(parametersMap.get("auth_username"), parametersMap.get("auth_password"), link.getConnetion())){
				System.out.println("login invalido!");
				return null;		
			}
			prep = link.getConnetion().prepareStatement("insert into properties values (?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, parametersMap.get("type"));
			prep.setString(2, parametersMap.get("description"));
			prep.setString(3, parametersMap.get("price"));
			prep.setString(4, parametersMap.get("location"));
			prep.setString(5, parametersMap.get("owner"));
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
					throw new ConnectionDatabaseException("N��o foi possivel fechar o ResultSet",e);
				}
			if(prep != null)
				try {
					prep.close();
				} catch (SQLException e) {
					throw new ConnectionDatabaseException("N��o foi possivel fechar o Statement",e);
				}
			if(link != null)
				link.closeConnection();



		}
		return list;
	}
}