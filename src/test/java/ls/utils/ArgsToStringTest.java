package ls.utils;

import static org.junit.Assert.assertEquals;
import ls.app.App;
import ls.exception.IllegalCommandException;

import org.junit.Test;

public class ArgsToStringTest {
	
	@Test(expected = IllegalCommandException.class)
	public void null_array() throws IllegalCommandException{
		App.argsToString(null);
	}
	
	@Test(expected = IllegalCommandException.class)
	public void empty_array() throws IllegalCommandException{
		String [] a = new String[0];
		App.argsToString(a);
	}
	
	@Test
	public void commandArray() throws IllegalCommandException{
		String [] args = {"GET", "/users"}; 
		String [] args2 = {"POST",  "/users",  "auth_username=superadmin&auth_password=ls1213&username=teste&password=testepass&email=teste@teste.pt&fullname=teste+teste"};
		assertEquals("GET /users", App.argsToString(args));
		assertEquals(App.argsToString(args2), "POST /users auth_username=superadmin&auth_password=ls1213&username=teste&password=testepass&email=teste@teste.pt&fullname=teste+teste");
	}

}
