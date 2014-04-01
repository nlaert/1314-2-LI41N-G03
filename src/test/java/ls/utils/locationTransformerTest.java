package ls.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import Exception.IllegalCommandException;

public class locationTransformerTest {
	
	@Test
	public void no_separator_occurrence() throws IllegalCommandException{
		String command = "Test Test";
		String command2 = "Test";
		assertEquals(command, Utils.locationTransformer(command));
		assertEquals(command2, Utils.locationTransformer(command2));
	}
	
	@Test
	public void separator_transformed() throws IllegalCommandException{
		String command = "Lisboa|Olivais";
		String commandTransformed = "Lisboa, Olivais";
		String command2 = "GET /properties/location/Lisboa|Olivais";
		String command2Transformed = "GET /properties/location/Lisboa, Olivais";
		assertEquals(commandTransformed, Utils.locationTransformer(command));
		assertEquals(command2Transformed, Utils.locationTransformer(command2));
	}
	
	@Test(expected = IllegalCommandException.class)
	public void invalid_strings_Null() throws IllegalCommandException{
		Utils.locationTransformer(null);
	}
	@Test(expected = IllegalCommandException.class)
	public void invalid_strings_Empty() throws IllegalCommandException{
		Utils.locationTransformer("");
	}

}
