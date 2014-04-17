package ls.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import ls.exception.IllegalCommandException;

import org.junit.Test;

public class PathParametersTest {

	@Test(expected  = IllegalCommandException.class)
	public void null_command_Test() throws IllegalCommandException{
		assertNull(Utils.pathParameters(null,null));
	}
	
	@Test
	public void path_without_parameters_Test() throws IllegalCommandException{
		String path = "GET /users";
		String command = "GET /users";
		String [] parameters = {};
		assertTrue(Arrays.equals(Utils.pathParameters(path, command), parameters));
	}
	
	@Test
	public void path_with_separated_parameters_Test() throws IllegalCommandException{
		String path = "GET /properties/{pid}/rentals/{year}/{cw}";
		String command = "GET /properties/1/rental/2014/20";
		String [] parameters = {"1", "2014", "20"};
		assertTrue(Arrays.equals(Utils.pathParameters(path, command), parameters));
	}
}
