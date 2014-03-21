package ls.project;

public class app {

	public static void main(String[] args)
	{
		Gestao gest = new Gestao("GET /users/joao");
		Gestao gest0 = new Gestao("GET /users/pedro");
		Gestao gest1 = new Gestao("GET /users");
		Gestao gest2 = new Gestao("GET /");
	}
}
	

