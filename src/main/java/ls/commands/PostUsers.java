package ls.commands;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class PostUsers implements ICommand {

	DataBaseManager link;
	PreparedStatement prep;
	@Override
	public ArrayList<String> execute(String[] command, HashMap<String, String> map) throws IllegalCommandException,ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try{
			HashMap<String, String> parametersMap = Utils.mapper(command[2]);
			link = new DataBaseManager();
			if (!Utils.checkAuth(parametersMap.get("auth_username"), parametersMap.get("auth_password"), link.getConnetion())){
				System.out.println("login invalido!");
				return null;		
			}
			prep = link.getConnetion().prepareStatement("insert into Users values (?, ?, ?, ?)");
			prep.setString(1, parametersMap.get("username"));
			prep.setString(2, parametersMap.get("password"));
			prep.setString(3, parametersMap.get("email"));
			prep.setString(4, parametersMap.get("fullname"));
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
