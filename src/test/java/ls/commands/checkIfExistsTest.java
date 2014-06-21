package ls.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.jdbc.DataBaseManager;

import org.junit.Test;

public class checkIfExistsTest {

	@Test
	public void new_User_Exists_Test() throws SQLException, ConnectionDatabaseException, FileException{
		CRUD.executeNonQuery("Insert into users values ('userTest', 'pass', 'test@test.com', 'test test')");
		String cmd = "select email from users where username = ? and email = ?";
		String [] params = {"userTest", "test@test.com"};
		DataBaseManager db = new DataBaseManager();
		assertTrue(CommandsUtils.checkIfExists(cmd, params, db.getConnetion()));
		CRUD.executeNonQuery("delete from users where username = 'userTest'");
	}
	
	@Test
	public void user_Not_Found_Test() throws SQLException, ConnectionDatabaseException, FileException{
		CRUD.executeNonQuery("delete from users where username = 'userTest'");
		String cmd = "select email from users where username = ? and email = ?";
		String [] params = {"userTest", "test@test.com"};
		DataBaseManager db = new DataBaseManager();
		assertFalse(CommandsUtils.checkIfExists(cmd, params, db.getConnetion()));
	}
}
