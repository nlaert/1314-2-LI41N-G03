package ls.commands;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.jdbc.CRUD;
import ls.propertiesRental.Rental;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCommands {
	
	static int pid;
	static String insertClient1 = "insert into users values('testeJUNIT1','junit1','junit1@junit.com','junit Teste 1')";
	static String insertClient2 = "insert into users values('testeJUNIT2','junit2','junit2@junit.com','junit Teste 2')";
	static String insertPropertyClient1 = "insert into properties values"
			+ "('villa','villa ao pe do mar',1000, 'Peniche, Peniche', 'testeJUNIT1')";
	
	static String selectPidFromPropertyClient1 ="select pid from properties where [owner] = 'testeJUNIT1'";
	
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
	
			//		Inserir um cliente = 1
			CRUD.executeNonQuery(insertClient1);
			//	Inserir outro cliente = 2
			CRUD.executeNonQuery(insertClient2);
			//	Inserir uma propriedade ao cliente 1
			CRUD.executeNonQuery(insertPropertyClient1);
			//	Saber o PID da propriedade
			ArrayList<String> list = CRUD.executeQuery(selectPidFromPropertyClient1);
			pid = Integer.parseInt(list.get(0).substring(0, list.get(0).indexOf("\t")));
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
	public void Get_User_With_Username_Test() throws IllegalCommandException, ConnectionDatabaseException, SQLException
	{
		
		String [] user = {"GET", "/users/joao"};
		ICommand ex1 = gest.find(user, map);
		assertTrue(ex1 instanceof GetUserUsername);
		//CRUD.executeQuery("select user from users where user = 'joao'");
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
	
	@Test
	public void Delete_Rental_Pending_Test() throws ConnectionDatabaseException, IllegalCommandException
	{
		
		
////		Inserir um cliente = 1
//		CRUD.executeNonQuery(insertClient1);
////		Inserir outro cliente = 2
//		CRUD.executeNonQuery(insertClient2);
////		Inserir uma propriedade ao cliente 1
//		CRUD.executeNonQuery(insertPropertyClient1);
////		Saber o PID da propriedade
//		ArrayList<String> list = CRUD.executeQuery(selectPidFromPropertyClient1);
//		pid = Integer.parseInt(list.get(0).substring(0, list.get(0).indexOf("\t")));
//		Inserir um rental do cliente 2 
		String insertRentalOnClient2 = "insert into rental values("+pid+",'testeJUNIT2',2100,12,'pending',GETDATE(),null)";
		CRUD.executeNonQuery(insertRentalOnClient2);
//		Eliminar aluguer do cliente 2
		String [] delete = {"DELETE", "/properties/"+pid+"/rentals/"+2100+"/"+12,"auth_username=testeJUNIT2&auth_password=junit2"};
		ArrayList<String> list = new ArrayList<String>();
		ICommand ex1 = gest.find(delete, map);
		if(ex1!= null){
			list=ex1.execute(map);
		}
		assertEquals(list.get(1),"1");
//		CRUD.executeNonQuery(deletePropertyClient1);
//		CRUD.executeNonQuery(deleteCliente1);
//		CRUD.executeNonQuery(deleteCliente2);
			
		
	}
	@Test(expected= IllegalCommandException.class)
	public void Delete_Rental_confirmed_Test() throws ConnectionDatabaseException, IllegalCommandException
	{
//		String selectPidFromPropertyClient1 ="select pid from properties where [owner] = 'testeJUNIT1'";
//		
////		Inserir um cliente = 1
//		CRUD.executeNonQuery(insertClient1);
////		Inserir outro cliente = 2
//		CRUD.executeNonQuery(insertClient2);
////		Inserir uma propriedade ao cliente 1
//		CRUD.executeNonQuery(insertPropertyClient1);
////		Saber o PID da propriedade
//		ArrayList<String> list = CRUD.executeQuery(selectPidFromPropertyClient1);
//		pid = Integer.parseInt(list.get(0).substring(0, list.get(0).indexOf("\t")));
//		Inserir um rental do cliente 2 
		String insertRentalOnClient2 = "insert into rental values("+pid+",'testeJUNIT2',2100,12,'confirmed',GETDATE(),GETDATE())";
		CRUD.executeNonQuery(insertRentalOnClient2);
//		Eliminar aluguer do cliente 2
		String [] delete = {"DELETE", "/properties/"+pid+"/rentals/"+2100+"/"+12,"auth_username=testeJUNIT2&auth_password=junit2"};
		ArrayList<String> list = new ArrayList<String>();
		ICommand ex1 = gest.find(delete, map);
		if(ex1!= null){
			list=ex1.execute(map);
		}
//		assertEquals(list.get(1),"1");
//		CRUD.executeNonQuery(deletePropertyClient1);
//		CRUD.executeNonQuery(deleteCliente1);
//		CRUD.executeNonQuery(deleteCliente2);
			
		
	}
	@Test
	public void Patch_Pending_Test() throws ConnectionDatabaseException, IllegalCommandException
	{
//		String selectPidFromPropertyClient1 ="select pid from properties where [owner] = 'testeJUNIT1'";
//
//		//	Inserir um cliente = 1
//		CRUD.executeNonQuery(insertClient1);
//		//	Inserir outro cliente = 2
//		CRUD.executeNonQuery(insertClient2);
//		//	Inserir uma propriedade ao cliente 1
//		CRUD.executeNonQuery(insertPropertyClient1);
//		//	Saber o PID da propriedade
//		ArrayList<String> list = CRUD.executeQuery(selectPidFromPropertyClient1);
//		pid = Integer.parseInt(list.get(0).substring(0, list.get(0).indexOf("\t")));
		//	Inserir um rental do cliente 2 
		String insertRentalOnClient2 = "insert into rental values("+pid+",'testeJUNIT2',2100,12,'pending',GETDATE(),null)";
		CRUD.executeNonQuery(insertRentalOnClient2);
		//	Eliminar aluguer do cliente 2
		String [] patch = {"PATCH", "/properties/"+pid+"/rentals/2100/12","auth_username=testeJUNIT1&auth_password=junit1"};
		ArrayList<String> list = new ArrayList<String>();
		ICommand ex1 = gest.find(patch, map);
		if(ex1!= null){
			list=ex1.execute(map);
		}
		assertEquals(list.get(1),"1");
//		CRUD.executeNonQuery(deletePropertyClient1);
//		CRUD.executeNonQuery(deleteCliente1);
//		CRUD.executeNonQuery(deleteCliente2);


	}

	
	
	@After
	public void clean() throws ConnectionDatabaseException
	{
		
		String deleteRentalClient2 = "delete from rental where [renter] = 'testeJUNIT2'";
		CRUD.executeNonQuery(deleteRentalClient2);
		
	}
	
	
	
	@AfterClass
	public static void tearDown() throws SQLException, ConnectionDatabaseException
	{
		String deletePropertyClient1 = "delete from properties where [owner] = 'testeJUNIT1'";
		String deleteCliente1 = "delete from users where username = 'testeJUNIT1'";
		String deleteCliente2 = "delete from users where username = 'testeJUNIT2'";
		CRUD.executeNonQuery(deletePropertyClient1);
		CRUD.executeNonQuery(deleteCliente1);
		CRUD.executeNonQuery(deleteCliente2);
	}
}
