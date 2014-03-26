package ls.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import Exception.ClosingDataAccessException;
import Exception.CommandsException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class GetUsers implements iCommand {

	private HashMap<String,iCommand> map;
	Statement stmt;
	
	ResultSet rs;
	DataBaseManager link;
	
	
	public GetUsers()
	{
		map = new HashMap<String,iCommand>();
		map.put("/",new GetUserUsername());
	}

	@Override
	public void execute(String command) throws ClosingDataAccessException, CommandsException {

		if(command.equals(""))
		{
			selectWithoutUser();
			return;

		}

		String [] keyValue = Utils.limitator(command);
		if(map.containsKey(keyValue[0]))
		{
			map.get(keyValue[0]).execute(keyValue[1]);
		}
		else
		{
			keyValue[1] = keyValue[0].substring(1);
			keyValue[0] = keyValue[0].substring(0,1);
			if(map.containsKey(keyValue[0]))
			{
				map.get(keyValue[0]).execute(keyValue[1]);
			}

		}


	}
	
	private void selectWithoutUser() throws CommandsException, ClosingDataAccessException{
		try {
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery("select username, password, email, fullname from users");
			System.out.println("Username \t\t Password \t\t Email \t\t\t\t Fullname ");
			while(rs.next())
			{
				System.out.format("%s \t\t\t %s \t\t\t %s \t\t %s \n", rs.getString(1),rs.getString(2), 
						rs.getString(3), rs.getString(4));
			}
			System.out.println();
		} catch (SQLException e) {
			throw new CommandsException("Não é possivel retornar a lista de utilizadores", e);
		} finally
		{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new ClosingDataAccessException("Não é possivel fechar o ResultSet", e);
				}
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new ClosingDataAccessException("Não é possivel fechar o Preparement", e);
				}
			if(link != null)
				link.closeConnection();
			
		}
	}

}
