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
		String result = "[{\"username\":\"nick\",\"password\":\"pass\",\"email\":\"a35466@alunos.isel.pt\",\"fullname\":\"Nick Laert\"},"
						+ "{\"username\":\"joao\",\"password\":\"pass\",\"email\":\"a35392@alunos.isel.pt\",\"fullname\":\"Joao Rodrigues\"}]";
		assertEquals(JSON.jsonify(aux), result);
	}
	
	@Test
	public void empty_params(){
		assertEquals(JSON.jsonify(null), "{}");
		assertEquals(JSON.jsonify(new ArrayList<String>()), "{}");
	}
	
	@Test
	public void one_Row_Test(){
		ArrayList<String> aux = new ArrayList<String>();
		aux.add("username	password	email	fullname");
		aux.add("nick	pass	a35466@alunos.isel.pt	Nick Laert");
		String result = "{\"username\":\"nick\",\"password\":\"pass\",\"email\":\"a35466@alunos.isel.pt\",\"fullname\":\"Nick Laert\"}";
		assertEquals(JSON.jsonify(aux), result);
	}
	
	@Test
	public void number_values_Test(){
		ArrayList<String> aux = new ArrayList<String>();
		aux.add("pid	type	description	price	location");
		aux.add("1	apartment	apartamento nos Olivais	1000	Lisboa, Olivais");
		String result = "{\"pid\":1,\"type\":\"apartment\",\"description\":\"apartamento nos Olivais\",\"price\":1000,\"location\":\"Lisboa, Olivais\"}";
		assertEquals(JSON.jsonify(aux), result);

	}
}
