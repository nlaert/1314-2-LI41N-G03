package ls.utils;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class mapperTest {
	HashMap <String, String> map = new HashMap<String, String>();
	
	@Test
	public void mapper_invalid_string(){
		assertEquals(null, Utils.mapper(""));
		assertEquals(null, Utils.mapper(null));
	}
	
	@Test
	public void mapper_expected_values(){
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
