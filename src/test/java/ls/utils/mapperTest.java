package ls.utils;

import static org.junit.Assert.*;

import java.util.HashMap;

import ls.exception.IllegalCommandException;

import org.junit.Test;

public class mapperTest {
	HashMap <String, String> map = new HashMap<String, String>();
	
	@Test(expected = IllegalCommandException.class)
	public void mapper_invalid_string_Test() throws IllegalCommandException{
		assertEquals(null, Utils.mapper(""));
		assertEquals(null, Utils.mapper(null));
	}
	
	@Test
	public void mapper_expected_values_Test() throws IllegalCommandException{
		StringBuilder builder = new StringBuilder();
		String [] keys = {"username", "password", "fullname", "email"}, 
				values = {"lsuser", "lspass", "lsname", "ls@email.com"};
		for (int i = 0; i < keys.length; i++){
			if(i>0)
				builder.append('&');
			builder.append(keys[i] + "=" + values[i]);
			map.put(keys[i], values[i]);
		}
		assertEquals(map, Utils.mapper(builder.toString()));
	}

}
