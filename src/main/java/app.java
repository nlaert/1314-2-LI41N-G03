

import ls.commands.*;
import ls.propertiesRental.Commands;
import ls.utils.Utils;
import Exception.IllegalCommandException;
import Exception.ConnectionDatabaseException;

public class App {

	public static void main(String[] args) throws IllegalCommandException, ConnectionDatabaseException 
	{		
		Commands gest = new Commands();
		gest.add("GET /users", new GetUsers());
		gest.add("GET /users/", new GetUserUsername());
		gest.add("GET /properties", new GetProperties());
		gest.add("GET /properties/details/", new GetPropertiesDetails());
		gest.add("GET /properties/location/", new GetPropertiesLocation());
		gest.add("GET /properties/owner/", new GetPropertiesOwner());
		gest.add("GET /properties/type/", new GetPropertiesType());
		gest.add("POST /users", new PostUsers());
		gest.add("POST /properties", new PostProperties());
		
		String command = Utils.argsToString(args);
		iCommand cmd = gest.find(command);
		Utils.printArrayList(cmd.execute(command));
		
//		iCommand ex1 = gest.find("GET /users");
//		if(ex1 != null)
//			Utils.printArrayList(ex1.execute("GET /users"));
//		iCommand ex2 = gest.find("GET /users/joao");
//		if(ex2 != null)
//			Utils.printArrayList(ex2.execute("GET /users/joao"));
//		iCommand ex3 = gest.find("GET /properties");
//		if(ex3 != null)
//			Utils.printArrayList(ex3.execute("GET /properties"));
//		iCommand ex4 = gest.find("GET /properties/details/1");
//		if(ex4 != null)
//			Utils.printArrayList(ex4.execute("GET /properties/details/1"));
//		iCommand ex5 = gest.find("GET /properties/location/Lisboa|Olivais");
//		if(ex5 != null)
//			Utils.printArrayList(ex5.execute("GET /properties/location/Lisboa|Olivais"));
//		iCommand ex6 = gest.find("GET /properties/owner/joao");
//		if(ex6 != null)
//			Utils.printArrayList(ex6.execute("GET /properties/owner/joao"));
//		iCommand ex7 = gest.find("GET /properties/type/apartment");
//		if(ex7 != null)
//			Utils.printArrayList(ex7.execute("GET /properties/type/apartment"));
//		
//		iCommand ex8 = gest.find("POST /users auth_username=superadmin&auth_password=ls1213&username=teste&password=testepass&email=teste@teste.pt&fullname=teste+teste");
//		if(ex8 != null)
//		{
//			ex8.execute("POST /users auth_username=superadmin&auth_password=ls1213&username=teste&password=testepass&email=teste@teste.pt&fullname=teste+teste");
//		}
//		iCommand ex9 = gest.find("POST /properties auth_username=superadmin&auth_password=ls1213"
//				+ "&type=apartment&description=Apartamento+em+Peniche&price=1000&location=Peniche|Peniche&owner=nick");
//		if(ex8 != null)
//		{
//			ex9.execute("POST /properties auth_username=superadmin&auth_password=ls1213"
//					+ "&type=apartment&description=Apartamento+em+Peniche&price=1000&location=Peniche|Peniche&owner=nick");
//		}
//
	}
}


