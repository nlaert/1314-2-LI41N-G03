package jdbcTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {
	private DataBaseManager link;
	ResultSet rs;
	Statement stmt;
	
	public CRUD() throws SQLException
	{
		select();
		insert();
		update();
		select();
		delete();
		select();
	}

	private void select() throws SQLException {
		try{
			link = new DataBaseManager();
			String cmdSel = "select bi, nome from Aluno";
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery(cmdSel);
			System.out.println("----------SELECT---------");
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
	
	private void insert() throws SQLException{
		try{
			link = new DataBaseManager();
			String cmdSel = "insert into Aluno values (3,'José')";
			stmt = link.getConnetion().createStatement();
			int rows = stmt.executeUpdate(cmdSel);
			System.out.println("----------INSERT---------");
			if (rows==1)
				System.out.println("Aluno introduzido com sucesso!!");
			else
				System.out.println("Falha ao introduzir novo aluno");
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
	
	private void update() throws SQLException{
		try{
			link = new DataBaseManager();
			String cmdSel = "update Aluno set nome = 'Nick Laert' where bi = 1";
			stmt = link.getConnetion().createStatement();
			int rows = stmt.executeUpdate(cmdSel);
			System.out.println("----------UPDATE---------");
			if (rows==1)
				System.out.println("Aluno actualizado com sucesso!!");
			else
				System.out.println("Falha ao actualizar aluno");
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
	
	private void delete() throws SQLException{
		try{
			link = new DataBaseManager();
			String cmdSel = "delete from Aluno where bi = 1";
			stmt = link.getConnetion().createStatement();
			int rows = stmt.executeUpdate(cmdSel);
			System.out.println("----------DELETE---------");
			if (rows==1)
				System.out.println("Aluno apagado com sucesso!!");
			else
				System.out.println("Falha ao apagar aluno");
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
