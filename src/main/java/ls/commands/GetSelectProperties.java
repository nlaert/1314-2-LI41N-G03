package ls.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Exception.ClosingDataAccessException;
import Exception.CommandsException;
import ls.jdbc.DataBaseManager;

public class GetSelectProperties implements iCommand {
	
	Statement stmt;
	PreparedStatement prep;
	ResultSet rs;
	DataBaseManager link;
	
	private String source;
	public GetSelectProperties(String source)
	{
		this.source = source;
	}
	@Override
	public void execute(String command) throws CommandsException, ClosingDataAccessException {
		try {
			link = new DataBaseManager();
			prep = link.getConnetion().prepareStatement("select [type], [description], [price], [location] from properties where "+source+" = ?");
			if(source.equals("pid"))
				prep.setInt(1, Integer.parseInt(command));
			else
				prep.setString(1,command);
			
			rs = prep.executeQuery();
			System.out.println("Type \t\t\t Description \t\t\t\t Price \t\t\t Location ");
			while(rs.next())
			{
				System.out.format("%s \t\t %s \t\t %s \t\t %s \n", rs.getString(1),rs.getString(2), 
						rs.getString(3), rs.getString(4));
			}
			System.out.println();
		} catch (SQLException e) {
			System.out.println(e);
		} finally
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
					throw new ClosingDataAccessException("Não foi possivel fechar o statement",e);
				}
			if(link != null)
				link.closeConnection();
			
		}

	}

}
