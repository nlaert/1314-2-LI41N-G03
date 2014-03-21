

import java.sql.SQLException;

import ls.propertiesRental.Gestao;

public class app {

	public static void main(String[] args) throws SQLException
	{
		Gestao gest = new Gestao("GET /users");
		Gestao gest1 = new Gestao("GET /properties/location/Lisboa, Olivais");
		Gestao gest2 = new Gestao("GET /properties/details/1");
		Gestao gest3 = new Gestao("GET /properties/owner/joao");
////		Gestao gest2 = new Gestao("GET /");
	}
}
	

