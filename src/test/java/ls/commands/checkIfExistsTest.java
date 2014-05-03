package ls.commands;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import ls.exception.ConnectionDatabaseException;
import ls.jdbc.CRUD;
import ls.jdbc.DataBaseManager;

public class checkIfExistsTest {
	
//	@Before
//	public void setUp() throws ConnectionDatabaseException, SQLException{
//		CRUD.executeNonQuery("Insert into users values ('userTest', 'pass', 'test@test.com', 'test test'");
//	}
//	
	@Test
	public void new_User_Exists_Test() throws SQLException, ConnectionDatabaseException{
		CRUD.executeNonQuery("Insert into users values ('userTest', 'pass', 'test@test.com', 'test test'");
		String cmd = "select email from users where username = ? and email = ?";
		String [] params = {"userTest", "test@test.com"};
		DataBaseManager db = new DataBaseManager();
		assertTrue(CommandsUtils.checkIfExists(cmd, params, db.getConnetion()));
		CRUD.executeNonQuery("delete from users where username = 'userTest'");
	}
	
	@Test
	public void user_Not_Found_Test() throws SQLException, ConnectionDatabaseException{
		CRUD.executeNonQuery("delete from users where username = 'userTest'");
		String cmd = "select email from users where username = ? and email = ?";
		String [] params = {"userTest", "test@test.com"};
		DataBaseManager db = new DataBaseManager();
		assertFalse(CommandsUtils.checkIfExists(cmd, params, db.getConnetion()));
	}
	
//	@AfterClass
//	public static void tearDown() throws ConnectionDatabaseException, SQLException{
//		CRUD.executeNonQuery("delete from users where username = 'userTest'");
//	}

}
