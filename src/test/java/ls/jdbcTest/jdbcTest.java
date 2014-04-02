package ls.jdbcTest;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ls.exception.ConnectionDatabaseException;
import ls.jdbc.*;
import static org.junit.Assert.*;

public class jdbcTest {
	
	int aluno1 = 100;
	int aluno2 = 101;
	String cmdDeleteAluno1 = "delete from Aluno where bi ="+aluno1;
	String cmdDeleteAluno2 = "delete from Aluno where bi ="+aluno2;
	String cmdUpdateAluno2 = "update Aluno set nome='JOAO RODRIGUES' where bi="+aluno2;
	String cmdInsertAluno1 = "insert into Aluno values("+aluno1+",'NICK')";
	String cmdInsertAluno2 = "insert into Aluno values("+aluno2+",'JOAO')";
	
	@Before
	public void setUp() throws SQLException, ConnectionDatabaseException
	{
		CRUD.executeNonQuery(cmdInsertAluno1);
		CRUD.executeNonQuery(cmdInsertAluno2);
	}
	
	@Test
	public void test_Select() throws SQLException, ConnectionDatabaseException
	{
		String select = "select bi, nome from Aluno where bi >= " + aluno1 + " and bi <= " + aluno2;
		ArrayList <String> result = new ArrayList <String>(2);
		result.add(aluno1 + "\tNICK\t");
		result.add(aluno2 + "\tJOAO\t");
		assertEquals(result, CRUD.executeQuery(select));
	}
	
	@Test(expected = SQLException.class)
	public void test_Insert_Duplicate() throws SQLException, ConnectionDatabaseException
	{	CRUD.executeNonQuery(cmdDeleteAluno2);
		assertEquals(1, CRUD.executeNonQuery(cmdInsertAluno2));
		CRUD.executeNonQuery(cmdInsertAluno2);
	}
	
	@Test
	public void test_Update() throws SQLException, ConnectionDatabaseException
	{
		assertEquals(1, CRUD.executeNonQuery(cmdUpdateAluno2));
	}
	
	@Test
	public void test_Delete() throws SQLException, ConnectionDatabaseException
	{
		
		assertEquals(1, CRUD.executeNonQuery(cmdDeleteAluno1));
	}
	
	@After
	public void tearDown() throws SQLException, ConnectionDatabaseException
	{
		CRUD.executeNonQuery(cmdDeleteAluno1);
		CRUD.executeNonQuery(cmdDeleteAluno2);
	}
	
	
	
}
