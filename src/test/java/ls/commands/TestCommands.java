package ls.commands;

import static org.junit.Assert.*;
import ls.propertiesRental.Commands;

import org.junit.Before;
import org.junit.Test;

import Exception.CloseConnectionException;
import Exception.ClosingDataAccessException;
import Exception.CommandsException;
import Exception.IllegalCommandException;

public class TestCommands {

	
	
	Commands gest;
	@Before
	public void setUp() throws CommandsException, ClosingDataAccessException, CloseConnectionException
	{
		gest = new Commands();
		gest.add("GET /users", new GetUsers());
		gest.add("GET /users/{username}", new GetUserUsername());
		gest.add("GET /properties", new GetProperties());
		gest.add("GET /properties/details/{pid}", new GetPropertiesDetails());
		gest.add("GET /properties/location/{location}", new GetPropertiesLocation());
		gest.add("GET /properties/owner/{owner}", new GetPropertiesOwner());
		gest.add("GET /properties/type/{type}", new GetPropertiesType());
		gest.add("POST /users auth_username=superadmin&auth_password=ls1213&username=teste&password=testepass&email=teste@teste.pt&fullname=teste+teste", new PostUsers());
		
	}
	@Test
	public void Get_All_Users() throws CommandsException, ClosingDataAccessException, CloseConnectionException
	{
		iCommand ex1 = gest.find("GET /users");
		assertTrue(ex1 instanceof GetUsers);
		
	}
	@Test
	public void Get_User_With_Username() throws CommandsException, ClosingDataAccessException, CloseConnectionException
	{
		iCommand ex1 = gest.find("GET /users/joao");
		assertTrue(ex1 instanceof GetUserUsername);
	}
	
	
	
}
