package ls.commands;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.CRUD;
import ls.propertiesRental.Rental;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCommands {
	
	static String deletePostUsers = "delete from Users where user = 'testeJUNIT'";
	String insertPostUsers = "insert into users values('testeJUNIT','junit','junit@junit.com','junit Teste'";
	HashMap<String, String> map;
	
	static Rental gest;
	@BeforeClass
	public static void setUp() throws IllegalCommandException, ConnectionDatabaseException
	{
		gest = new Rental();
		gest.add("GET /users", new GetUsers());
		gest.add("GET /users/{username}", new GetUserUsername());
		gest.add("GET /properties", new GetProperties());
		gest.add("GET /properties/details/{pid}", new GetPropertiesDetails());
		gest.add("GET /properties/location/{location}", new GetPropertiesLocation());
		gest.add("GET /properties/owner/{owner}", new GetPropertiesOwner());
		gest.add("GET /properties/type/{type}", new GetPropertiesType());
		gest.add("GET /users/{username}/rentals", new GetUsersRentals());
		gest.add("GET /users/{username}/properties/owned", new GetUsersPropertiesOwned());
		gest.add("GET /properties/{pid}/rentals", new GetPropertiesRentals());
		gest.add("GET /properties/{pid}/rentals/{year}/{cw}", new GetPropertiesRentalsWithDate());
		gest.add("POST /users", new PostUsers());
		gest.add("POST /properties", new PostProperties());
		gest.add("POST /properties/{pid}/rentals", new PostPropertiesRentals());
		gest.add("PATCH /properties/{pid}/rentals/{year}/{cw}", new PatchPropertiesRentals());
		gest.add("DELETE /properties/{pid}/rentals/{year}/{cw}", new DeletePropertiesRental());
//		CRUD.executeNonQuery("insert into users values('testeJUNIT','junit','junit@junit.com','junit Teste'");
	
	}
	
	@Before
	public void cleanMap()
	{
		map = new HashMap<String, String>();
	}
	//gets dos users e das properties
	@Test
	public void Get_All_Users_And_All_Properties_Test() throws IllegalCommandException
	{
		String [] users = {"GET", "/users"};
		String [] properties = {"GET", "/properties"};

		ICommand ex1 = gest.find(users, map);
		assertTrue(ex1 instanceof GetUsers);
		ex1 = gest.find(properties, map);
		assertTrue(ex1 instanceof GetProperties);
		
	}
	
	//get de um especifico user
	@Test
	public void Get_User_With_Username_Test() throws IllegalCommandException
	{
		String [] user = {"GET", "/users/joao"};
		ICommand ex1 = gest.find(user, map);
		assertTrue(ex1 instanceof GetUserUsername);
	}
	
	
	@Test
	public void Get_Properties_Location_Test() throws IllegalCommandException
	{
		String [] properties = {"GET", "/properties/location/Peniche|Peniche"};
		ICommand ex1 = gest.find(properties, map);
		assertTrue(ex1 instanceof GetPropertiesLocation);
	}	
	
	@Test
	public void Post_Users_Test() throws IllegalCommandException, ConnectionDatabaseException
	{
		String [] command = {"POST", "/users", "auth_username=superadmin&auth_password=ls1213&username=testeJUNIT&password=testeJUNIT&email=testeJUNIT@teste.pt&fullname=teste+JUNIT"};
		ICommand ex1 = gest.find(command, map);
		assertTrue(ex1 instanceof PostUsers);
	}
	
	@Test(expected = ConnectionDatabaseException.class)
	public void Post_Users_Without_Authentication_Test() throws IllegalCommandException, ConnectionDatabaseException
	{
		String [] command = {"POST", "/users", "username=testeJUNIT&password=testeJUNIT&email=testeJUNIT@teste.pt&fullname=teste+JUNIT"};
		ICommand ex1 = gest.find(command, map);
		if(ex1!=null)
			ex1.execute(map);
	}
	
	@Test(expected = IllegalCommandException.class)
	public void Empty_Command_Test() throws IllegalCommandException, ConnectionDatabaseException 
	{
		ICommand ex1 = gest.find(null, null);
		if(ex1!= null)
			ex1.execute(null);	
	}
	
	@Test(expected = IllegalCommandException.class)
	public void Get_Wrong_Command_Test() throws IllegalCommandException, ConnectionDatabaseException 
	{
		String [] get = {"GET", ""};
		ICommand ex1 = gest.find(get, map);
		if(ex1!= null)
			ex1.execute(null);
	}
	@Test(expected = IllegalCommandException.class)
	public void Get_Wrong_Command2_Test() throws IllegalCommandException, ConnectionDatabaseException 
	{
		String [] get = {"GET", "/"};
		ICommand ex1 = gest.find(get, map);
		if(ex1!= null)
			ex1.execute(null);
	}
	@Test(expected = IllegalCommandException.class)
	public void Post_Wrong_Command_Test() throws IllegalCommandException, ConnectionDatabaseException 
	{
		String [] post = {"POST", ""};
		ICommand ex1 = gest.find(post, map);
		if(ex1!= null)
			ex1.execute(null);
	}
	@Test(expected = IllegalCommandException.class)
	public void Post_Wrong_Command2_Test() throws IllegalCommandException, ConnectionDatabaseException 
	{
		String [] post = {"POST", "/"};
		ICommand ex1 = gest.find(post, map);
		if(ex1!= null)
			ex1.execute(null);
	}
	
	
	@Test(expected = IllegalCommandException.class)
	public void Post_Users_Wrong_Command_Test() throws IllegalCommandException, ConnectionDatabaseException 
	{
		String [] post = {"POST", "/users", ""};
		ICommand ex1 = gest.find(post, map);
		if(ex1!= null)
			ex1.execute(null);
	}
	@Test(expected = IllegalCommandException.class)
	public void Get_Users_Wrong_Command_Test() throws IllegalCommandException, ConnectionDatabaseException 
	{
		String [] get = {"GET", "/users", ""};
		ICommand ex1 = gest.find(get, map);
		if(ex1!= null)
			ex1.execute(null);
	}
	
	
	public void Delete_Rental_With_Year_And_Cw() throws ConnectionDatabaseException
	{
		CRUD.executeNonQuery(insertPostUsers);
//		CRUD.executeNonQuery(deletePostUsers);
	}
	
	
	
	
	
	
	
	@AfterClass
	public static void tearDown() throws SQLException, ConnectionDatabaseException
	{
		CRUD.executeNonQuery(deletePostUsers);
	}
}
