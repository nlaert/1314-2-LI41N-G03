package ls.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class locationTransformerTest {
	
	@Test
	public void no_separator_occurrence(){
		String command = "Test Test";
		String command2 = "Test";
		assertEquals(command, Utils.locationTransformer(command));
		assertEquals(command2, Utils.locationTransformer(command2));
	}
	
	@Test
	public void separator_transformed(){
		String command = "Lisboa|Olivais";
		String commandTransformed = "Lisboa, Olivais";
		String command2 = "GET /properties/location/Lisboa|Olivais";
		String command2Transformed = "GET /properties/location/Lisboa, Olivais";
		assertEquals(commandTransformed, Utils.locationTransformer(command));
		assertEquals(command2Transformed, Utils.locationTransformer(command2));
	}
	
	@Test
	public void invalid_strings(){
		assertNull(Utils.locationTransformer(null));
		assertNull(Utils.locationTransformer(""));
	}

}
