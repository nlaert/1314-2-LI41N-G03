package ls.output;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.result.ICommandResult;
import ls.commands.result.UsersResult;
import ls.db.User;
import ls.exception.AppException;
import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;
import ls.output.html.HtmlPage;
import ls.output.html.view.HtmlView;
import ls.output.html.view.UsersView;
import ls.output.json.view.JsonView;
import ls.rentalManager.RentalManager;

import org.junit.BeforeClass;
import org.junit.Test;



public class ViewTest {
	
	static RentalManager gest;
	static ICommandResult commandResult;
	static ICommandResult emptyCommandResult;
	
	@BeforeClass
	public static void setUp() throws IllegalCommandException, ConnectionDatabaseException{
		User u1 = new User("nick", "pass", "a35466@alunos.isel.pt", "Nick Laert");
		User u2 = new User("joao", "pass", "a35392@alunos.isel.pt", "Joao Rodrigues");
		ArrayList<User> aux = new ArrayList<User>();
		aux.add(u1);
		aux.add(u2);
		commandResult = new UsersResult(aux);
		
		ArrayList<User> emptyList = new ArrayList<User>();
		emptyCommandResult= new UsersResult(emptyList);
		
		gest = new RentalManager();
		gest.addView(UsersResult.class, UsersView.class);
	}
	
	@Test
	public void JSON_multiple_Rows_Test() throws FileException, IOException{
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("output-file", "ViewTest");
		Output.send(new JsonView(commandResult,map),map);
		
		String resultOutput = OutputReadBuffer(map);
		String result = "[{\"Username\":\"nick\",\"Password\":\"pass\",\"Email\":\"a35466@alunos.isel.pt\",\"FullName\":\"Nick Laert\"},"
				+ "{\"Username\":\"joao\",\"Password\":\"pass\",\"Email\":\"a35392@alunos.isel.pt\",\"FullName\":\"Joao Rodrigues\"}]";
		
		assertEquals(result, resultOutput);
	}
	
	@Test
	public void JSON_empty_test() throws FileException, IOException{
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("output-file", "ViewTest");
		Output.send(new JsonView(emptyCommandResult,map),map);
		String resultOutput = OutputReadBuffer(map);
		assertEquals("{}", resultOutput);
	}
	
	@Test
	public void HTML_multiple_Rows_Test() throws AppException, IOException{
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("output-file", "ViewTest");
		HtmlView v;
		HtmlPage hp;
		v = gest.getView();

		hp = v.getView(commandResult, map);
		Output.send(hp, map);
		String resultOutput = OutputReadBuffer(map);

		String expected = "<tr><td><a href='/users/nick'>nick</a></td><td>a35466@alunos.isel.pt</td><td>Nick Laert</td></tr>";
		String expected2 = "<tr><td><a href='/users/joao'>joao</a></td><td>a35392@alunos.isel.pt</td><td>Joao Rodrigues</td></tr>";
		assertTrue(resultOutput.contains(expected));
		assertTrue(resultOutput.contains(expected2));	
	}
	
	@Test public void HTML_empty_test() throws IllegalCommandException, FileException, IOException{
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("output-file", "ViewTest");
		HtmlView v;
		HtmlPage hp;
		v = gest.getView();

		hp = v.getView(emptyCommandResult, map);
		Output.send(hp, map);
		String resultOutput = OutputReadBuffer(map);
		String expected = "<html><head><Title>Users</Title></head><body><h1>All Users</h1><br></br><table style='width:600px' border='1'><tr><th>Username<th>email<th>Fullname</th></th></th></tr></table>";
		assertTrue(resultOutput.contains(expected));
	}

	private static String OutputReadBuffer(HashMap<String, String> map) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(map.get("output-file")));
			String line;
			while ((line = reader.readLine()) != null)
			{
				result.append(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			reader.close();
		}
		return result.toString();
	}
}