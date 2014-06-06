package ls.http.server;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.db.IType;
import ls.exception.AppException;
import ls.exception.AuthenticationException;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.http.response.HttpResponse;
import ls.http.response.HttpStatusCode;
import ls.output.html.HtmlPage;
import ls.output.html.view.BadRequestView;
import ls.output.html.view.HomePageView;
import ls.output.html.view.View;
import ls.propertiesRental.Rental;

@SuppressWarnings("serial")
public class Servlet extends HttpServlet {
    
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
            resolveHttpHandler(req, resp).send(resp);
        }catch(Throwable th) {
            // No exception should go unnoticed!
            new HttpResponse(HttpStatusCode.InternalServerError).send(resp);
            ServerHTTP.trace(th.getMessage());
        }
    }       
   	    
    @SuppressWarnings("unchecked")
	private HttpResponse resolveHttpHandler(HttpServletRequest req, HttpServletResponse resp) throws IOException, URISyntaxException, IllegalCommandException, ConnectionDatabaseException, AuthenticationException {
        URI reqUri = new URI(req.getRequestURI());
        if(reqUri.getPath().equals("/"))
        {
        	return new HttpResponse(HttpStatusCode.Ok, new HomePageView());
        }
        String [] command = null;         
        
        if(req.getMethod().equals("GET"))
        {
        	 command = new String[2];
             command[0] = req.getMethod();
             command[1] = reqUri.getPath();
        }
        HashMap <String,String> htmlParameters=null;
        HashMap <String,String> commandParameters = new HashMap<String, String>();
        
        if(req.getMethod().equals("POST"))
        {
        	htmlParameters = (HashMap<String, String>) FormUrlEncoded.retrieveFrom(req);
        	commandParameters.putAll(htmlParameters);
        	command = new String[2];
            command[0] = req.getMethod();
            command[1] = reqUri.getPath();
        }
       
       
        
        Rental gest = ServerHTTP.getRental();
        
        ICommand<IType> cmd = null;
        ICommandResult<IType> result = null;
        try{
        	cmd = gest.find(command,commandParameters);
			result = cmd.execute(commandParameters);
        }
        catch(AuthenticationException e)
		{
			return new HttpResponse(HttpStatusCode.NotAuthorized);
		}
		catch(IllegalCommandException e)
		{
			return new HttpResponse(HttpStatusCode.NotFound, new BadRequestView());
		}
		HtmlPage htmlPage;
		View view = gest.getView();
		try {
			htmlPage = view.getView(result, commandParameters);
		} catch (AppException e) {
			return new HttpResponse(HttpStatusCode.BadRequest,new BadRequestView());
		}
		if(htmlPage == null)
			return new HttpResponse(HttpStatusCode.BadRequest,new BadRequestView());
		
		return new HttpResponse(HttpStatusCode.Ok, htmlPage);
       
    }


}

