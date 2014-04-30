package ls.output;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;



public class HTMLTest {
	@Test
	public void multiple_Rows_Test(){
		ArrayList<String> aux = new ArrayList<String>();
		aux.add("username	password	email	fullname");
		aux.add("nick	pass	a35466@alunos.isel.pt	Nick Laert");
		aux.add("joao	pass	a35392@alunos.isel.pt	Joao Rodrigues");
		System.out.println(HTML.htmlify(aux));
		String result = "<html><body><table border =\"1\"><tr><th>username</th><th>password</th><th>email</th><th>fullname</th></tr>"
		+"<tr><td>nick</td><td>pass</td><td>a35466@alunos.isel.pt</td><td>Nick Laert</td></tr><tr>"
		+"<td>joao</td><td>pass</td><td>a35392@alunos.isel.pt</td><td>Joao Rodrigues</td></tr></table></body></html>";
		assertEquals(HTML.htmlify(aux), result);
		
		
	}
	@Test
	public void empty_params(){
		assertEquals("", HTML.htmlify(null));
		assertEquals("", HTML.htmlify(new ArrayList<String>()));
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
