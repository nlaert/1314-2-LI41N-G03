package ls.http.server;

import ls.propertiesRental.RentalManager;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;




public class ServerHTTP {

    /*
     * TCP port where to listen. Standard port for HTTP is 80 but might be
     * already in use
     */
	private static RentalManager gest;
    private int listenPort;
    private Server server;
    public ServerHTTP(RentalManager gest, Integer port)
    {
    	this.listenPort = port;
    	this.gest = gest;
    }
    
    public static  RentalManager getRental()
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
    	server = new Server(listenPort);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(Servlet.class, "/");
        server.start();
        System.out.println("Server is started at port: " + listenPort);
    }
    
    public void stopServer() throws Exception{
    	if (server != null){
    		server.stop();
    		System.out.println("Server is stopped, bye");
    	}
    	else
    		System.out.println("Server is not running");//TODO create ServerException
    }
}
