package ls.output;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class JSONTest {

	@Test
	public void multiple_Rows_Test(){
		ArrayList<String> aux = new ArrayList<String>();
		aux.add("username	password	email	fullname");
		aux.add("nick	pass	a35466@alunos.isel.pt	Nick Laert");
		aux.add("joao	pass	a35392@alunos.isel.pt	Joao Rodrigues");
		System.out.println(JSON.jsonify(aux));
		String result = "[{\"username\":\"nick\",\"password\":\"pass\",\"email\":\"a35466@alunos.isel.pt\",\"fullname\":\"Nick Laert\"},"
						+ "{\"username\":\"joao\",\"password\":\"pass\",\"email\":\"a35392@alunos.isel.pt\",\"fullname\":\"Joao Rodrigues\"}]";
		assertEquals(JSON.jsonify(aux), result);
	}
	
	public void empty_params(){
		assertEquals("{}", JSON.jsonify(null));
		assertEquals("{}", JSON.jsonify(new ArrayList<String>()));
	}
}
