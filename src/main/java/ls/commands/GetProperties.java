package ls.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import Exception.IllegalCommandException;
import Exception.ConnectionDatabaseException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class GetProperties implements iCommand {

	Statement stmt;
	ResultSet rs;
	DataBaseManager link;

	@Override
	public ArrayList<String> execute(String command) throws IllegalCommandException, ConnectionDatabaseException {
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
			throw new IllegalCommandException("Não foi possivel retornar a lista de todas as propriedades",e);
		} 
		finally
		{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionDatabaseException("Não foi possivel fechar o ResultSet",e);
				}
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new ConnectionDatabaseException("Não foi possivel fechar o Statement",e);
				}
			if(link != null)
				link.closeConnection();

		}
	}
}


