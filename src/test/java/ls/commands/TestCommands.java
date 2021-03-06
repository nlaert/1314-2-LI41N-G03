package ls.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.properties.GetProperties;
import ls.commands.properties.GetPropertiesDetails;
import ls.commands.properties.GetPropertiesLocation;
import ls.commands.properties.GetPropertiesOwner;
import ls.commands.properties.GetPropertiesType;
import ls.commands.properties.PostProperties;
import ls.commands.rentals.DeletePropertiesRental;
import ls.commands.rentals.GetPropertiesRentals;
import ls.commands.rentals.GetPropertiesRentalsWithDate;
import ls.commands.rentals.GetUsersRentals;
import ls.commands.rentals.PatchPropertiesRentals;
import ls.commands.rentals.PostPropertiesRentals;
import ls.commands.result.ICommandResult;
import ls.commands.users.GetUserUsername;
import ls.commands.users.GetUsers;
import ls.commands.users.PostUsers;
import ls.db.IType;
import ls.exception.AuthenticationException;
import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;
import ls.rentalManager.RentalManager;

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
	
	static RentalManager gest;
	@BeforeClass
	public static void setUp() throws IllegalCommandException, ConnectionDatabaseException, FileException
	{
		gest = new RentalManager();
		gest.add("GET /users", new GetUsers());
		gest.add("GET /users/{username}", new GetUserUsername());
		gest.add("GET /properties", new GetProperties());
		gest.add("GET /properties/details/{pid}", new GetPropertiesDetails());
		gest.add("GET /properties/location/{location}", new GetPropertiesLocation());
		gest.add("GET /properties/owner/{owner}", new GetPropertiesOwner());
		gest.add("GET /properties/type/{type}", new GetPropertiesType());
		gest.add("GET /users/{username}/rentals", new GetUsersRentals());
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
			pid = Integer.parseInt(list.get(1).substring(0, list.get(1).indexOf("\t")));
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

		ICommand<?> ex1 = gest.find(users, map);
		assertTrue(ex1 instanceof GetUsers);
		ex1 = gest.find(properties, map);
		assertTrue(ex1 instanceof GetProperties);
		
	}
	
	//get de um especifico user
	@Test
	public void Get_User_With_Username_Test() throws IllegalCommandException, ConnectionDatabaseException, SQLException, AuthenticationException, FileException
	{
		String [] user = {"GET", "/users/testeJUNIT1"};
		ICommand<IType> ex1 = gest.find(user, map);
		ICommandResult<IType> list = ex1.execute(map);
		assertTrue(list.getSize()==1);
	}
	
	
	@Test
	public void Get_Properties_Location_Test() throws IllegalCommandException, ConnectionDatabaseException, AuthenticationException, FileException
	{
		String [] properties = {"GET", "/properties/location/Peniche|Peniche"};
		ICommand<IType> ex1 = gest.find(properties, map);
		ICommandResult<IType> list =ex1.execute(map); 
		assertTrue(list.getSize()>=1);
	}	
	
	@Test
	public void Post_Users_Test() throws IllegalCommandException, ConnectionDatabaseException, AuthenticationException, FileException
	{
		String username = "testeJUNIT3";
		String [] command = {"POST", "/users", "auth_username=superadmin&auth_password=ls1213&username=" + username + "&password=testeJUNIT&email=testeJUNIT30@teste.pt&fullname=teste+JUNIT"};
		ICommand<?> ex1 = gest.find(command, map);
		ex1.execute(map);
		String cmd = "Select username from users where username = ?";
		ArrayList<String> list = CRUD.executeQuery(cmd, new String [] {username});
		assertTrue(ex1 instanceof PostUsers);
		assertTrue(list.get(1).contains(username));
		CRUD.executeNonQuery("delete from users where username = ?", new String [] {username});
	}
	
	@Test(expected = AuthenticationException.class)
	public void Post_Users_Without_Authentication_Test() throws IllegalCommandException, ConnectionDatabaseException, AuthenticationException, FileException
	{
		String [] command = {"POST", "/users", "username=testeJUNIT&password=testeJUNIT&email=testeJUNIT@teste.pt&fullname=teste+JUNIT"};
		ICommand<?> ex1 = gest.find(command, map);
		if(ex1!=null)
			ex1.execute(map);
	}
	
	@Test(expected = IllegalCommandException.class)
	public void Empty_Command_Test() throws IllegalCommandException, ConnectionDatabaseException, AuthenticationException, FileException 
	{
		ICommand<?> ex1 = gest.find(null, null);
		if(ex1!= null)
			ex1.execute(null);	
	}
	
	@Test(expected = IllegalCommandException.class)
	public void Get_Wrong_Command_Test() throws IllegalCommandException, ConnectionDatabaseException, AuthenticationException, FileException 
	{
		String [] get = {"GET", ""};
		ICommand<?> ex1 = gest.find(get, map);
		if(ex1!= null)
			ex1.execute(null);
	}
	@Test(expected = IllegalCommandException.class)
	public void Get_Wrong_Command2_Test() throws IllegalCommandException, ConnectionDatabaseException, AuthenticationException, FileException 
	{
		String [] get = {"GET", "/"};
		ICommand<?> ex1 = gest.find(get, map);
		if(ex1!= null)
			ex1.execute(null);
	}
	@Test(expected = IllegalCommandException.class)
	public void Post_Wrong_Command_Test() throws IllegalCommandException, ConnectionDatabaseException, AuthenticationException, FileException 
	{
		String [] post = {"POST", ""};
		ICommand<?> ex1 = gest.find(post, map);
		if(ex1!= null)
			ex1.execute(null);
	}
	@Test(expected = IllegalCommandException.class)
	public void Post_Wrong_Command2_Test() throws IllegalCommandException, ConnectionDatabaseException, AuthenticationException, FileException 
	{
		String [] post = {"POST", "/"};
		ICommand<?> ex1 = gest.find(post, map);
		if(ex1!= null)
			ex1.execute(null);
	}
	
	
	@Test(expected = IllegalCommandException.class)
	public void Post_Users_Wrong_Command_Test() throws IllegalCommandException, ConnectionDatabaseException, AuthenticationException, FileException 
	{
		String [] post = {"POST", "/users", ""};
		ICommand<IType> ex1 = gest.find(post, map);
		if(ex1!= null)
			ex1.execute(null);
	}
	@Test(expected = IllegalCommandException.class)
	public void Get_Users_Wrong_Command_Test() throws IllegalCommandException, ConnectionDatabaseException, AuthenticationException, FileException 
	{
		String [] get = {"GET", "/users", ""};
		ICommand<IType> ex1 = gest.find(get, map);
		if(ex1!= null)
			ex1.execute(null);
	}
	
	@Test
	public void Delete_Rental_Pending_Test() throws ConnectionDatabaseException, IllegalCommandException, AuthenticationException, FileException
	{
//		Inserir um rental do cliente 2 
		String insertRentalOnClient2 = "insert into rental values("+pid+",'testeJUNIT2',2100,12,'pending',GETDATE(),null)";
		CRUD.executeNonQuery(insertRentalOnClient2);
//		Eliminar aluguer do cliente 2
		String [] delete = {"DELETE", "/properties/"+pid+"/rentals/"+2100+"/"+12,"auth_username=testeJUNIT2&auth_password=junit2"};
		ICommandResult<IType> list = null;
		ICommand<IType> ex1 = gest.find(delete, map);
		if(ex1!= null){
			list=ex1.execute(map);
		}
		assertEquals(list.getArrayList().get(1),"1");
	}
	
	@Test(expected= IllegalCommandException.class)
	public void Delete_Rental_confirmed_Test() throws ConnectionDatabaseException, IllegalCommandException, AuthenticationException, FileException
	{

//		Inserir um rental do cliente 2 
		String insertRentalOnClient2 = "insert into rental values("+pid+",'testeJUNIT2',2100,12,'confirmed',GETDATE(),GETDATE())";
		CRUD.executeNonQuery(insertRentalOnClient2);
//		Eliminar aluguer do cliente 2
		String [] delete = {"DELETE", "/properties/"+pid+"/rentals/"+2100+"/"+12,"auth_username=testeJUNIT2&auth_password=junit2"};
		ICommand<IType> ex1 = gest.find(delete, map);
		if(ex1!= null){
			ex1.execute(map);
		}
			
		
	}
	@Test
	public void Patch_Pending_Test() throws ConnectionDatabaseException, IllegalCommandException, AuthenticationException, FileException
	{
		//	Inserir um rental do cliente 2 
		String insertRentalOnClient2 = "insert into rental values("+pid+",'testeJUNIT2',2100,12,'pending',GETDATE(),null)";
		CRUD.executeNonQuery(insertRentalOnClient2);
		//	Eliminar aluguer do cliente 2
		String [] patch = {"PATCH", "/properties/"+pid+"/rentals/2100/12","auth_username=testeJUNIT1&auth_password=junit1"};
		ICommandResult<IType> list = null;
		ICommand<IType> ex1 = gest.find(patch, map);
		if(ex1!= null){
			list=ex1.execute(map);
		}
		assertEquals(list.getArrayList().get(1),"1");
	}
	
	@After
	public void clean() throws ConnectionDatabaseException, FileException
	{
		String deleteRentalClient2 = "delete from rental where [renter] = 'testeJUNIT2'";
		CRUD.executeNonQuery(deleteRentalClient2);
	}
	
	@AfterClass
	public static void tearDown() throws SQLException, ConnectionDatabaseException, FileException
	{
		String deletePropertyClient1 = "delete from properties where [owner] = 'testeJUNIT1'";
		String deleteCliente1 = "delete from users where username = 'testeJUNIT1'";
		String deleteCliente2 = "delete from users where username = 'testeJUNIT2'";
		CRUD.executeNonQuery(deletePropertyClient1);
		CRUD.executeNonQuery(deleteCliente1);
		CRUD.executeNonQuery(deleteCliente2);
	}
}
