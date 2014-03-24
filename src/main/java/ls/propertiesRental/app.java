package ls.propertiesRental;

import java.sql.SQLException;

public class app {

	public static void main(String[] args) throws SQLException
	{
//		Gestao gest = new Gestao("GET /users");
//		Gestao gest1 = new Gestao("GET /properties/location/Lisboa, Olivais");
//		Gestao gest0 = new Gestao("GET /users/pedro");
//		Gestao gest1 = new Gestao("GET /users");
////		Gestao gest2 = new Gestao("GET /");
		Gestao gest6 = new Gestao("POST /users auth_username=superadmin&auth_password=ls1213&username=teste&password=testepass&email=teste@teste.pt&fullname=teste+teste");
	}
}
	

