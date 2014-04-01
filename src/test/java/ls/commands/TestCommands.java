package ls.commands;

import static org.junit.Assert.*;

import ls.propertiesRental.Commands;

import org.junit.Before;
import org.junit.Test;
import Exception.ConnectionDatabaseException;
import Exception.IllegalCommandException;

public class TestCommands {

	
	
	Commands gest;
	@Before
	public void setUp() throws IllegalCommandException, ConnectionDatabaseException
	{
		gest = new Commands();
		gest.add("GET /users", new GetUsers());
		gest.add("GET /users/", new GetUserUsername());
		gest.add("GET /properties", new GetProperties());
		gest.add("GET /properties/details/", new GetPropertiesDetails());
		gest.add("GET /properties/location/", new GetPropertiesLocation());
		gest.add("GET /properties/owner/", new GetPropertiesOwner());
		gest.add("GET /properties/type/", new GetPropertiesType());
		gest.add("POST /users", new PostUsers());
		gest.add("POST /properties", new PostProperties());
	}
	
	@Test
	public void Get_All_Users_And_All_Properties_Test() throws IllegalCommandException
	{
		iCommand ex1 = gest.find("GET /users");
		assertTrue(ex1 instanceof GetUsers);
		ex1 = gest.find("GET /properties");
		assertTrue(ex1 instanceof GetProperties);
		
	}
	@Test
	public void Get_User_With_Username_Test() throws IllegalCommandException
	{
		iCommand ex1 = gest.find("GET /users/joao");
		assertTrue(ex1 instanceof GetUserUsername);
	}
	
	@Test
	public void Get_Properties_Location_Test() throws IllegalCommandException
	{
		iCommand ex1 = gest.find("GET /properties/location/Peniche, Peniche");
		assertTrue(ex1 instanceof GetPropertiesLocation);
	}
	@Test
	public void Post_Users_Test() throws IllegalCommandException
	{
		iCommand ex1 = gest.find("POST /users auth_username=superadmin&auth_password=ls1213&username=testeJUNIT&password=testeJUNIT&email=testeJUNIT@teste.pt&fullname=teste+JUNIT");
		assertTrue(ex1 instanceof PostUsers);
	}
	@Test(expected = IllegalCommandException.class)
	public void Get_Wrong_Command_Test() throws IllegalCommandException, ConnectionDatabaseException 
	{
		iCommand ex1 = gest.find("GET ");
		if(ex1!= null)
			ex1.execute("GET ");
	}
	@Test(expected = IllegalCommandException.class)
	public void Post_Wrong_Command_Test() throws IllegalCommandException, ConnectionDatabaseException 
	{
		iCommand ex1 = gest.find("POST ");
		if(ex1!= null)
			ex1.execute("POST ");
		
		
	}
	
	@Test(expected = IllegalCommandException.class)
	public void Empty_Command_Test() throws IllegalCommandException, ConnectionDatabaseException 
	{
		iCommand ex1 = gest.find("");
		if(ex1!= null)
			ex1.execute("");
		
	}
	@Test(expected = IllegalCommandException.class)
	public void Post_Users_Wrong_Command_Test() throws IllegalCommandException, ConnectionDatabaseException 
	{
		iCommand ex1 = gest.find("POST /users ");
		if(ex1!= null)
			ex1.execute("POST /users ");
		ex1 = gest.find("POST /users");
		if(ex1!= null)
			ex1.execute("POST /users");
		
	}
}
