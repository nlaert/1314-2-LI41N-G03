package ls.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import Exception.CloseConnectionException;
import Exception.ClosingDataAccessException;
import Exception.CommandsException;
import Exception.OpenConnectionException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class GetProperties implements iCommand {
	
	Statement stmt;
	ResultSet rs;
	DataBaseManager link;
	
	private HashMap<String,iCommand> map;
	public GetProperties()
	{
		map = new HashMap<String,iCommand>();
		map.put("/details", new GetSelectProperties("pid"));
		map.put("/location", new GetSelectProperties("location"));
		map.put("/owner", new GetSelectProperties("owner"));
		map.put("/type", new GetSelectProperties("type"));
		
	}

	@Override
	public ArrayList<String> execute(String command) throws ClosingDataAccessException, CommandsException, CloseConnectionException {
		ArrayList<String> list = new ArrayList<String>();
		if(command.equals(""))
		{
			list = selectAllProperties();
			return list;
		}
		String [] keyValue = Utils.limitator(command);
		if(map.containsKey(keyValue[0]))
		{
			keyValue[1] = keyValue[1].substring(1);
			list = map.get(keyValue[0]).execute(keyValue[1]);
		}
		else
			System.out.println("Informacao nao encontrada!");
		return list;
		
	}

	private ArrayList<String> selectAllProperties() throws CommandsException, ClosingDataAccessException, CloseConnectionException {
		ArrayList<String> list = new ArrayList<String>();
		try {
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery("select [type], [description], [price], [location] from properties");
//			System.out.println("Type \t Description \t Price \t\t\t\t Location ");
			list = Utils.resultSetToArrayList(rs);
//			while(rs.next())
//			{
//				System.out.format("%s \t\t %s \t\t %s \t\t %s \n", rs.getString(1),rs.getString(2), 
//						rs.getString(3), rs.getString(4));
//			}
//			System.out.println();
			return list;
		} catch (SQLException e) {
			throw new CommandsException("Não foi possivel retornar a lista de todas as propriedades",e);
		} 
		finally
		{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ClosingDataAccessException("Não foi possivel fechar o ResultSet",e);
				}
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new ClosingDataAccessException("Não foi possivel fechar o Statement",e);
				}
			if(link != null)
					link.closeConnection();

		}
	}

}


