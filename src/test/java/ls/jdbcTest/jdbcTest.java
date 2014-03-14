package ls.jdbcTest;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ls.jdbc.*;
import static org.junit.Assert.*;

public class jdbcTest {
	
	int aluno1 = 100;
	int aluno2 = 200;
	String cmdDeleteAluno1 = "delete from Aluno where bi ="+aluno1;
	String cmdDeleteAluno2 = "delete from Aluno where bi ="+aluno2;
	String cmdUpdateAluno2 = "update Aluno set nome='JOAO RODRIGUES' where bi="+aluno2;
	String cmdInsertAluno1 = "insert into Aluno values("+aluno1+",'NICK')";
	String cmdInsertAluno2 = "insert into Aluno values("+aluno2+",'JOAO')";
	
	@Before
	public void setUp() throws SQLException
	{
		CRUD.executeNonQuery(cmdInsertAluno1);
		CRUD.executeNonQuery(cmdInsertAluno2);
	}
	
	@Test
	public void test_Select()
	{
		
	}
	
	@Test(expected = SQLException.class)
	public void test_Insert_Duplicate() throws SQLException 
	{	CRUD.executeNonQuery(cmdDeleteAluno2);
		assertEquals(1, CRUD.executeNonQuery(cmdInsertAluno2));
		CRUD.executeNonQuery(cmdInsertAluno2);
	}
	
	@Test
	public void test_Update() throws SQLException
	{
		assertEquals(1, CRUD.executeNonQuery(cmdUpdateAluno2));
	}
	
	@Test
	public void test_Delete() throws SQLException
	{
		
		assertEquals(1, CRUD.executeNonQuery(cmdDeleteAluno1));
	}
	
	@After
	public void tearDown() throws SQLException
	{
		CRUD.executeNonQuery(cmdDeleteAluno1);
		CRUD.executeNonQuery(cmdDeleteAluno2);
	}
	
	
	
}
