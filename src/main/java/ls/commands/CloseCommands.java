package ls.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ls.exception.ConnectionDatabaseException;
import ls.jdbc.DataBaseManager;

public abstract class CloseCommands {

	
	public void close(Statement stmt, DataBaseManager link) throws ConnectionDatabaseException
	{
		close(null,stmt,link);
	}
	
	
	public void close(ResultSet rs, Statement stmt, DataBaseManager link) throws ConnectionDatabaseException
	{
		if(rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new ConnectionDatabaseException("Nao foi possivel fechar o ResultSet", e);
			}
		if(stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new ConnectionDatabaseException("Nao foi possivel fechar o Preparement", e);
			}
		if(link != null)
			link.closeConnection();
	}
	
	
}
