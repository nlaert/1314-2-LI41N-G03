package ls.http.server;

import ls.propertiesRental.Rental;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;




public class ServerHTTP {

    /*
     * TCP port where to listen. Standard port for HTTP is 80 but might be
     * already in use
     */
	private static Rental gest;
    private static final int LISTEN_PORT = 8000;
    public ServerHTTP(Rental gest)
    {
    	this.gest = gest;
    }
    
    public static  Rental getRental()
    {
    	return gest;
    }
//    public static HashMap<ICommandResult,ITypeView> getCommandsResults()
//    {
//    	return commandsResults;
//    }
    public static void trace(String msg) {
        System.out.println(msg);
    }
    
   
    public void initServer() throws Exception
    {
    	
    	
    	Server server = new Server(LISTEN_PORT);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(Servlet.class, "/");
        server.start();
        System.out.println("Server is started");
        
        System.in.read();
        server.stop();
        System.out.println("Server is stopped, bye");        
    }
/*
    public static void main(String[] args) throws Exception {
    	
    	_repo.add(new ToDo("Users in DB"));
        _repo.add(new ToDo("Properties in DB"));
    	
    	Server server = new Server(LISTEN_PORT);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(ToDoServlet.class, "/users");
        server.start();
        System.out.println("Server is started");
        
        System.in.read();
        server.stop();
        System.out.println("Server is stopped, bye");        
    }
    */
}
