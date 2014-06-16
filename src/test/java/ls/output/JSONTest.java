package ls.output;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.result.ICommandResult;
import ls.commands.result.UsersResult;
import ls.db.IType;
import ls.db.User;
import ls.exception.FileException;
import ls.propertiesRental.RentalManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JSONTest {
	RentalManager gest;
	HashMap<String, String> map;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@Test
	public void multiple_Rows_Test() throws FileException{
		User u1 = new User("nick", "pass", "a35466@alunos.isel.pt", "Nick Laert");
		User u2 = new User("joao", "pass", "a35392@alunos.isel.pt", "Joao Rodrigues");
		ArrayList<User> aux = new ArrayList<User>();
		aux.add(u1);
		aux.add(u2);
		ICommandResult commandResult = new UsersResult(aux);
		HashMap<String,String> map = new HashMap<String,String>();
		String result = "[{\"Username\":\"nick\",\"Password\":\"pass\",\"Email\":\"a35466@alunos.isel.pt\",\"FullName\":\"Nick Laert\"},"
				+ "{\"Username\":\"joao\",\"Password\":\"pass\",\"Email\":\"a35392@alunos.isel.pt\",\"FullName\":\"Joao Rodrigues\"}]";
		JSON.jsonify(commandResult, map);
		assertEquals(result, outContent.toString());
	}
	
//	@Test
//	public void empty_params(){
//		assertEquals(JSON.jsonify(null, null), "{}");
//		assertEquals(JSON.jsonify(new ArrayList<String>()), "{}");
//	}
//	
//	@Test
//	public void one_Row_Test(){
//		ArrayList<String> aux = new ArrayList<String>();
//		aux.add("username	password	email	fullname");
//		aux.add("nick	pass	a35466@alunos.isel.pt	Nick Laert");
//		String result = "{\"username\":\"nick\",\"password\":\"pass\",\"email\":\"a35466@alunos.isel.pt\",\"fullname\":\"Nick Laert\"}";
//		assertEquals(JSON.jsonify(aux), result);
//	}
//	
//	@Test
//	public void number_values_Test(){
//		ArrayList<String> aux = new ArrayList<String>();
//		aux.add("pid	type	description	price	location");
//		aux.add("1	apartment	apartamento nos Olivais	1000	Lisboa, Olivais");
//		String result = "{\"pid\":1,\"type\":\"apartment\",\"description\":\"apartamento nos Olivais\",\"price\":1000,\"location\":\"Lisboa, Olivais\"}";
//		assertEquals(JSON.jsonify(aux), result);
//
//	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
}
