package ls.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {
	private static DataBaseManager link;
	static ResultSet rs;
	static Statement stmt;
	
	
	public static void select() throws SQLException {

		link = new DataBaseManager();
		String cmdSel = "select bi, nome from Aluno";
		stmt = link.getConnetion().createStatement();
		rs = stmt.executeQuery(cmdSel);
		//			System.out.println("----------SELECT---------");
		//			while(rs.next())
		//			{
		//				System.out.format(" %s \t %s  \n",rs.getString(1),rs.getString(2));
		//			}
		
		if(rs != null)
			rs.close();
		if(stmt != null)
			stmt.close();
		if(link != null)
			link.closeConnection();
		
	}

	public static int executeNonQuery(String cmd) throws SQLException{
		int rows = 0;
		link = new DataBaseManager();
		stmt = link.getConnetion().createStatement();
		rows = stmt.executeUpdate(cmd);


		if(rs != null)
			rs.close();
		if(stmt != null)
			stmt.close();
		if(link != null)
			link.closeConnection();

		return rows;
	}
	
	
	
}
