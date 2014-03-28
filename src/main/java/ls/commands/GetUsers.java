package ls.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Exception.IllegalCommandException;
import Exception.ConnectionDatabaseException;
import ls.jdbc.DataBaseManager;
import ls.utils.Utils;

public class GetUsers implements iCommand {

	Statement stmt;
	ResultSet rs;
	DataBaseManager link;


	@Override
	public ArrayList<String> execute(String command) throws IllegalCommandException, ConnectionDatabaseException {
		ArrayList<String> list = new ArrayList<String>();
		try {
			link = new DataBaseManager();
			stmt = link.getConnetion().createStatement();
			rs = stmt.executeQuery("select username, password, email, fullname from users");
			list = Utils.resultSetToArrayList(rs);
			return list;
		} catch (SQLException e) {
			throw new IllegalCommandException("Não é possivel retornar a lista de utilizadores", e);
		} finally
		{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new ConnectionDatabaseException("Não é possivel fechar o ResultSet", e);
				}
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new ConnectionDatabaseException("Não é possivel fechar o Preparement", e);
				}
			if(link != null)

				link.closeConnection();


		}
	}
}