package ls.output;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.result.UsersResult;
import ls.db.User;
import ls.exception.AppException;
import ls.output.html.HtmlPage;
import ls.output.html.view.UsersView;
import ls.output.html.view.View;
import ls.output.html.view.ViewHtml;
import ls.propertiesRental.RentalManager;

import org.junit.Test;



public class HTMLTest {
	
	static RentalManager gest;
	
	
	public static void addViews()
	{
		gest.addView(UsersResult.class, UsersView.class);
	
	}
	
	@Test
	public void multiple_Rows_Test() throws AppException, IOException{
		String resultOutput="";
		gest = new RentalManager();
		addViews();

		User u1 = new User("nick", "pass", "a35466@alunos.isel.pt", "Laert");
		User u2 = new User("joao", "pass", "a35392@alunos.isel.pt", "Joao Rodrigues");
		ArrayList<User> aux = new ArrayList<User>();
		aux.add(u1);
		aux.add(u2);
		UsersResult commandResult = new UsersResult(aux);
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("output-file", "HTMLTest");
		int style = 150 * 4;
		ViewHtml v;
		HtmlPage hp;
		v = gest.getView();
		
			hp = v.getView(commandResult, map);
			Output.send(hp, map);
			resultOutput = OutputReadBuffer(map);
			

		
		String result = "<html><head><Title>Users</Title></head><body><h1>All Users</h1><table style=\"width:600px\" border=\"1\"><tr><th>Username<th>email<th>Fullname</th></th></th></tr><tr><td><a href='/users/nick'>nick</a><td>a35466@alunos.isel.pt<td>Laert</td></td></td></tr><tr><td><a href='/users/joao'>joao</a><td>a35392@alunos.isel.pt<td>Joao Rodrigues</td></td></td></tr></table style=\"width:600px\" border=\"1\"><h3><li><a href='/'>Menu</a></li></h3></body></html>";
		assertEquals(result, resultOutput);
		
		
	}
//	@Test
//	public void empty_params(){
//		assertEquals("", HTML.htmlify(null));
//		assertEquals("", HTML.htmlify(new ArrayList<String>()));
//	}

	private String OutputReadBuffer(HashMap<String, String> map) throws IOException {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			reader.close();
		}
		return result.toString();
	}
}
/*

<html>
    <body>
        
        <table  border="1">
            <tr>
                <th>username</th>
                <th>password</th>
                <th>email</th>
                <th>fullname</th>
            </tr>
            <tr>
                <td>nick</td>
                <td>pass</td> 
                <td>a35466@alunos.isel.pt</td>
                <td>Nick Laert</td>
            </tr>
            <tr>
                <td>joao</td>
                <td>pass</td> 
                <td>a35392@alunos.isel.pt</td>
                <td>Joao Rodrigues</td>
            </tr>
        </table>
    </body>

*/
