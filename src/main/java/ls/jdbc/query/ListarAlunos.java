package ls.jdbc.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ls.jdbc.ligacao.*;

public class ListarAlunos {
	private DataBaseManager link;
	ResultSet rs;
	Statement stmt;
	
	public ListarAlunos() throws SQLException
	{
		executa();
	}

	private void executa() throws SQLException {
		try{
			link = new DataBaseManager();
			String cmdSel = "select bi, nome from Aluno";
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery(cmdSel);
			
			while(rs.next())
			{
				System.out.format(" %s \t %s  \n",rs.getString(1),rs.getString(2));
			}
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}finally
		{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(link != null)
				link.closeConnection();
		}
		
		
	}
}
