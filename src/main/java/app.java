

import java.sql.SQLException;

import ls.propertiesRental.Gestao;

public class app {

	public static void main(String[] args) throws SQLException
	{
		Gestao gest = new Gestao("GET /users");
		Gestao gest1 = new Gestao("GET /properties/location/Lisboa, Olivais");
//		Gestao gest0 = new Gestao("GET /users/pedro");
//		Gestao gest1 = new Gestao("GET /users");
////		Gestao gest2 = new Gestao("GET /");
	}
}
	

