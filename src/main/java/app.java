

import java.sql.SQLException;

import ls.commands.*;
import ls.propertiesRental.Commands;
import ls.utils.Utils;
import Exception.CloseConnectionException;
import Exception.ClosingDataAccessException;
import Exception.CommandsException;

public class App {

	public static void main(String[] args) throws CommandsException, ClosingDataAccessException, CloseConnectionException 
	{
		
//		Gestao gest1 = new Gestao("GET /properties/location/Lisboa, Olivais");
//		Gestao gest0 = new Gestao("GET /users/pedro");
//		Gestao gest2 = new Gestao("GET /users");
//		Gestao gest3 = new Gestao("GET /");
//		Gestao gestPost0 = new Gestao("POST /users auth_username=superadmin&auth_password=ls1213&username=teste&password=testepass&email=teste@teste.pt&fullname=teste+teste");
//		Gestao gestGet0 = new Gestao("GET /users");
//		Gestao gestGet0 = new Gestao("GET /properties");
//		Gestao gestPost1 = new Gestao("POST /properties auth_username=superadmin&auth_password=ls1213"
//				+ "&type=apartment&description=Apartamento+em+Peniche&price=1000&location=Peniche,+Peniche&owner=nick");
//		Gestao gestGet1 = new Gestao("GET /properties");
//		Gestao gestGet2 = new Gestao("GET /users");
		
		Commands gest = new Commands();
		gest.add("GET /users", new GetUsers());
		gest.add("GET /users/{username}", new GetUserUsername());
		gest.add("GET /properties", new GetProperties());
		gest.add("GET /properties/details/{pid}", new GetPropertiesDetails());
		gest.add("GET /properties/location/{location}", new GetPropertiesLocation());
		gest.add("GET /properties/owner/{owner}", new GetPropertiesOwner());
		gest.add("GET /properties/type/{type}", new GetPropertiesType());
		gest.add("POST /users auth_username=superadmin&auth_password=ls1213&username=teste&password=testepass&email=teste@teste.pt&fullname=teste+teste", new PostUsers());
		
		
		if(ex1 != null)
			Utils.printArrayList(ex1.execute("GET /users"));
		iCommand ex2 = gest.find("GET /users/joao");
		if(ex2 != null)
			Utils.printArrayList(ex2.execute("GET /users/joao"));
		iCommand ex3 = gest.find("GET /properties");
		if(ex3 != null)
			Utils.printArrayList(ex3.execute("GET /properties"));
		iCommand ex4 = gest.find("GET /properties/details/1");
		if(ex4 != null)
			Utils.printArrayList(ex4.execute("GET /properties/details/1"));
		iCommand ex5 = gest.find("GET /properties/location/Lisboa, Olivais");
		if(ex5 != null)
			Utils.printArrayList(ex5.execute("GET /properties/location/Lisboa, Olivais"));
		iCommand ex6 = gest.find("GET /properties/owner/joao");
		if(ex6 != null)
			Utils.printArrayList(ex6.execute("GET /properties/owner/joao"));
		iCommand ex7 = gest.find("GET /properties/type/apartment");
		if(ex7 != null)
			Utils.printArrayList(ex7.execute("GET /properties/type/apartment"));
		iCommand ex8 = gest.find("POST /users auth_username=superadmin&auth_password=ls1213&username=teste&password=testepass&email=teste@teste.pt&fullname=teste+teste");
		if(ex8 != null)
		{
			ex8.execute("POST /users auth_username=superadmin&auth_password=ls1213&username=teste&password=testepass&email=teste@teste.pt&fullname=teste+teste");
		}
		
		
		
		
		
		
		
		
	}
}


