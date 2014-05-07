package ls.utils;

import ls.jdbc.DataBaseManager;

public class CheckAuth {

	String username = "test";
	String password = "password";
	String insertUser = "insert into Users values('" + username + "', '" + password + "', 'test@test.com', 'Tester')";
	DataBaseManager manager;
//	@Before
//	public void setUp() throws SQLException, ConnectionDatabaseException
//	{
//		CRUD.executeNonQuery(insertUser);
//		manager = new DataBaseManager();
//	}
//	
//	@After
//	public void tearDown() throws SQLException, ConnectionDatabaseException
//	{
//		CRUD.executeNonQuery("delete from Users where username = '" + username + "'");
//		manager.closeConnection();
//	}
//	
//	@Test
//	public void checkAuth_wrong_password() throws SQLException{
//		assertFalse(Utils.checkAuth(username, password + "a", manager.getConnetion()));
//	}
//	
//	@Test
//	public void checkAuth_null_password() throws SQLException{
//		assertFalse(Utils.checkAuth(username, null, manager.getConnetion()));
//	}
//	
//	@Test
//	public void checkAuth_wrong_username() throws SQLException{
//		assertFalse(Utils.checkAuth(username  + "a", password, manager.getConnetion()));
//	}
//	
//	@Test
//	public void checkAuth_correct_credentials() throws SQLException{
//		assertTrue(Utils.checkAuth(username, password, manager.getConnetion()));
//	}
}
