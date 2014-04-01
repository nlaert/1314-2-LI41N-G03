package ls.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Exception.IllegalCommandException;

public class ArgsToStringTest {
	
	@Test(expected = IllegalCommandException.class)
	public void null_array() throws IllegalCommandException{
		Utils.argsToString(null);
	}
	
	@Test(expected = IllegalCommandException.class)
	public void empty_array() throws IllegalCommandException{
		String [] a = new String[0];
		Utils.argsToString(a);
	}
	
	@Test
	public void commandArray() throws IllegalCommandException{
		String [] args = {"GET", "/users"}; 
		String [] args2 = {"POST",  "/users",  "auth_username=superadmin&auth_password=ls1213&username=teste&password=testepass&email=teste@teste.pt&fullname=teste+teste"};
		assertEquals("GET /users", Utils.argsToString(args));
		assertEquals(Utils.argsToString(args2), "POST /users auth_username=superadmin&auth_password=ls1213&username=teste&password=testepass&email=teste@teste.pt&fullname=teste+teste");
	}

}
