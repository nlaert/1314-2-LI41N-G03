

import java.sql.SQLException;

import ls.propertiesRental.Gestao;
import Exception.CloseConnectionException;
import Exception.ClosingDataAccessException;
import Exception.CommandsException;

public class App {

	public static void main(String[] args) throws CommandsException, ClosingDataAccessException, CloseConnectionException 
	{

		Gestao gest1 = new Gestao("GET /properties/location/Lisboa, Olivais");
//		Gestao gest0 = new Gestao("GET /users/pedro");
//		Gestao gest2 = new Gestao("GET /users");
//		Gestao gest3 = new Gestao("GET /");
//		Gestao gestPost0 = new Gestao("POST /users auth_username=superadmin&auth_password=ls1213&username=teste&password=testepass&email=teste@teste.pt&fullname=teste+teste");
//		Gestao gestGet0 = new Gestao("GET /users");
//		Gestao gestGet0 = new Gestao("GET /properties");
//		Gestao gestPost1 = new Gestao("POST /properties auth_username=superadmin&auth_password=ls1213"
//				+ "&type=apartment&description=Apartamento+em+Peniche&price=1000&location=Peniche,+Peniche&owner=nick");
//		Gestao gestGet1 = new Gestao("GET /properties");
	}
}


