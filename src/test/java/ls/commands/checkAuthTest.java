package ls.commands;

import java.sql.SQLException;

import ls.commands.CommandsUtils;
import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.jdbc.DataBaseManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class checkAuthTest {

	String username = "test";
	String password = "password";
	String insertUser = "insert into Users values('" + username + "', '" + password + "', 'test@test.com', 'Tester')";
	DataBaseManager manager;
	@Before
	public void setUp() throws SQLException, ConnectionDatabaseException, FileException
	{
		CRUD.executeNonQuery(insertUser);
		manager = new DataBaseManager();
	}
	
	@After
	public void tearDown() throws SQLException, ConnectionDatabaseException, FileException
	{
		CRUD.executeNonQuery("delete from Users where username = '" + username + "'");
		manager.closeConnection();
	}
	
	@Test
	public void checkAuth_wrong_password() throws SQLException{
		assertFalse(CommandsUtils.checkAuth(username, password + "a", manager.getConnetion()));
	}
	
	@Test
	public void checkAuth_null_password() throws SQLException{
		assertFalse(CommandsUtils.checkAuth(username, null, manager.getConnetion()));
	}
	
	@Test
	public void checkAuth_wrong_username() throws SQLException{
		assertFalse(CommandsUtils.checkAuth(username  + "a", password, manager.getConnetion()));
	}
	
	@Test
	public void checkAuth_correct_credentials() throws SQLException{
		assertTrue(CommandsUtils.checkAuth(username, password, manager.getConnetion()));
	}
}
