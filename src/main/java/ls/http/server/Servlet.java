package ls.http.server;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.db.IType;
import ls.db.Property;
import ls.db.Rental;
import ls.exception.AuthenticationException;
import ls.exception.ConnectionDatabaseException;
import ls.exception.FileException;
import ls.exception.IllegalCommandException;
import ls.http.response.HttpContent;
import ls.http.response.HttpResponse;
import ls.http.response.HttpStatusCode;
import ls.output.html.view.BadRequestView;
import ls.output.html.view.HomePageView;
import ls.output.html.view.HtmlView;
import ls.output.html.view.InternalServerErrorView;
import ls.output.json.view.JsonErrorView;
import ls.output.json.view.JsonView;
import ls.rentalManager.RentalManager;


public class Servlet extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
        			String.format("SERVER: Received %s request for %s", req.getMethod(), req.getRequestURI()));
            resolveHttpHandler(req, resp).send(resp);
        }
    	catch(Throwable th) {
            // No exception should go unnoticed!
    		new HttpResponse(HttpStatusCode.InternalServerError, new InternalServerErrorView(th.getMessage())).send(resp);
            
        }
    }       
   	    
	private HttpResponse resolveHttpHandler(HttpServletRequest req, HttpServletResponse resp) throws IOException, URISyntaxException, IllegalCommandException, ConnectionDatabaseException, AuthenticationException, FileException {
		String type = req.getHeader("accept");
		RentalManager gest = ServerHTTP.getRental();
        ICommand<IType> cmd = null;
        ICommandResult<IType> result = null;
        HttpContent page = null;
		
        
        
		URI reqUri = new URI(req.getRequestURI());
        if(reqUri.getPath().equals("/"))
        {
        	return new HttpResponse(HttpStatusCode.Ok, modelView(new HomePageView(), new JsonView(null,null),type));
        }
        String [] command = null;
        HashMap <String,String> commandParameters = new HashMap<String, String>();
        
        command = new String[2];
        command[0] = req.getMethod();
        command[1] = reqUri.getPath();
        
        if(req.getMethod().equals("POST"))
        {
        	if(command[1].contains("rentals"))
        		getProperty(req.getHeader("referer"), commandParameters);
        	try{
        		commandParameters.putAll(FormUrlEncoded.retrieveFrom(req));
        	}catch(IllegalCommandException e)
        	{
        		return new HttpResponse(HttpStatusCode.BadRequest, modelView(new BadRequestView(e.getMessage()),new JsonErrorView(e.getMessage()),type));
        	}
        	
        }
        
        try{
        	if(command[0].equals("POST"))
        		tryRestoreCurrentUser(req, commandParameters);
        	cmd = gest.find(command,commandParameters);
			result = cmd.execute(commandParameters);

        }
        catch(AuthenticationException e)
		{
			return new HttpResponse(HttpStatusCode.NotAuthorized);
		}
		catch(IllegalCommandException e)
		{
			
			return new HttpResponse(HttpStatusCode.BadRequest, modelView(new BadRequestView(e.getMessage()),new JsonErrorView(e.getMessage()),type));
		}
        catch(ConnectionDatabaseException e){
        	return new HttpResponse(HttpStatusCode.InternalServerError, modelView(new InternalServerErrorView(e.getMessage()),new JsonErrorView(e.getMessage()),type));
        }
        
        
        
        if(type.contains("text/html")){
        	
        	HtmlView<IType> view = gest.getView();
        	try {
    			page = view.getView(result, commandParameters);
    		} catch (IllegalCommandException e) {
    			return new HttpResponse(HttpStatusCode.BadRequest,new BadRequestView(e.getMessage()));
    		}
        }
        else if(type.contains("application/json"))
        {
        	page = new JsonView(result, commandParameters);
        }
        else 
        	return new HttpResponse(HttpStatusCode.BadRequest,new BadRequestView("Invalid Header(accept)"));
		
        
        if (command[0].equals("POST")){
			return new HttpResponse(HttpStatusCode.SeeOther).withHeader("location", seeOtherLocation(command,commandParameters,result));
		}
		
		
		return new HttpResponse(HttpStatusCode.Ok, page);
       
    }
	
	

	private void getProperty(String parameter,
			HashMap<String, String> commandParameters) {
		String[] pid = parameter.split("/");
		commandParameters.put("pid", pid[4]);
	}

	private String seeOtherLocation(String[] command,
			HashMap<String, String> commandParameters, ICommandResult<IType> result) {
		if(command[1].equals("/users"))
			return String.format("/users/%s", commandParameters.get("username"));
		if(command[1].equals("/properties")){
			Property p = (Property) result.getArrayList().get(0);
			return String.format("/properties/details/%d", p.pid);
		}
		if(command[1].equals("/properties/{pid}/rentals"))
		{
			Rental r = (Rental) result.getArrayList().get(0);
			return String.format("/properties/%d/rentals/%d/%d", r.property.pid, r.year, r.cw);
		}
		return "";
	}

	private void tryRestoreCurrentUser(HttpServletRequest req,
			HashMap<String, String> commandParameters) throws AuthenticationException {
		String auth = req.getHeader("Authorization");
		if(auth == null)
			throw new AuthenticationException("Invalid Login");
		
		auth = auth.split(" ")[1];
		Base64 b = new Base64();
		auth = new String(b.decode(auth));
		String [] parametersAuthentication = auth.split(":");
		commandParameters.put("auth_username", parametersAuthentication[0]);
		commandParameters.put("auth_password", parametersAuthentication[1]);
	}
	
	private HttpContent modelView(HttpContent html,HttpContent json, String type)
	{
		if(type.contains("text/html"))
			return html;
		else 
			return json;
	}
}

