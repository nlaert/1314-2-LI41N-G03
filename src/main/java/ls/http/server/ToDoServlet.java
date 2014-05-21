package ls.http.server;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ls.commands.ICommand;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.http.response.HttpStatusCode;
import ls.http.todo.ToDoRepository;
import ls.http.response.*;
import ls.output.HTML;

@SuppressWarnings("serial")
public class ToDoServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	doMethod(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	doMethod(req, resp);
    }
    
    private void doMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	try {
        	System.out.println(
        			String.format("Received %s request for %s", req.getMethod(), req.getRequestURI()));
            uglyHandle(req, resp).send(resp);
        }catch(Throwable th) {
            // No exception should go unnoticed!
            new HttpResponse(HttpStatusCode.InternalServerError).send(resp);
            ServerHTTP.trace(th.getMessage());
        }
    }       
   	    
    private HttpResponse uglyHandle(HttpServletRequest req, HttpServletResponse resp) throws IOException, URISyntaxException, IllegalCommandException, ConnectionDatabaseException {
    	ToDoRepository repo = ServerHTTP.getRepository();
        URI reqUri = new URI(req.getRequestURI());
        String [] command = null;
        if(req.getMethod().equals("GET"))
        {
        	 command = new String[2];
             command[0] = req.getMethod();
             command[1] = reqUri.getPath();
        }
       
//        String[] segs = reqUri.getPath().split("/");
        
        HashMap <String,String> map = new HashMap<String, String>(); 
        
		ICommand cmd = ServerHTTP.getRental().find(command,map);
		ArrayList result = cmd.execute(map);
        
		return new HttpResponse(HttpStatusCode.Ok);
        
//        if(segs.length < 2 || segs.length>3 || !segs[1].equals("todos")) {
//            return new HttpResponse(HttpStatusCode.NotFound);            
//        }
//        String method = req.getMethod();
//        if(!method.equals("GET") && !method.equals("POST")) {
//            return new HttpResponse(HttpStatusCode.MethodNotAllowed);            
//        }        
//        if(segs.length == 2) {
//            return method.equals("GET") ? 
//                    new ToDoController(repo).handleGetAllToDos(req)
//                    : new ToDoController(repo).handlePostToDos(req);                        
//        }
//        try(Scanner sc = new Scanner(segs[2])){
//	        if(!sc.hasNextInt()) {
//	            return new HttpResponse(HttpStatusCode.NotFound);
//	        }
//	        int id = sc.nextInt();
//	        return new ToDoController(repo).handleGetToDoById(req, id);
//        }
    } 

}

