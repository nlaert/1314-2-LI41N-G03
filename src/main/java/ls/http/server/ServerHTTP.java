package ls.http.server;

import ls.exception.ServerHttpException;
import ls.rentalManager.RentalManager;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ServerHTTP {

	private  static RentalManager gest;
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

    public static void trace(String msg) {
        System.out.println(msg);
    }
    
   
    public void initServer() throws ServerHttpException
    {
    	try{
    		server = new Server(listenPort);
    		ServletHandler handler = new ServletHandler();
    		server.setHandler(handler);
    		handler.addServletWithMapping(Servlet.class, "/");
    		server.start();
    		System.out.println("Server is started at port: " + listenPort);
    		
    	
    	}catch(Exception bi)
    	{
    		throw new ServerHttpException("There is already an instance of Http server");
    	}
    }
    
    public void stopServer() throws Exception{
    	if (server != null){
    		server.stop();
    		System.out.println("Server is stopped, bye");
    	}
    	else
    		System.out.println("Server is not running");
    }
}
