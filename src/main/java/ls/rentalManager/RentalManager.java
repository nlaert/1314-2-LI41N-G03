package ls.rentalManager;

import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.Commands;
import ls.commands.ICommand;
import ls.commands.result.ICommandResult;
import ls.db.IType;
import ls.exception.ConnectionDatabaseException;
import ls.exception.IllegalCommandException;
import ls.http.server.ServerHTTP;
import ls.output.Output;
import ls.output.html.view.HtmlView;
import ls.utils.Utils;

public class RentalManager {

	private HtmlView<IType> views;
	public ServerHTTP server;
	private ArrayList<Commands<IType>> list;
	private boolean activeServer;
	public RentalManager() throws IllegalCommandException, ConnectionDatabaseException
	{
		list = new ArrayList<Commands<IType>>();
		views = new HtmlView<IType>();
		activeServer = false;
	}
	public void add(String command, ICommand<?> e) 
	{
		//necessario verificar se ja existe ?
		list.add(new Commands(command, command.split("/").length, e));
	}
	
	public void addView(Class<?> commandResult, Class<?> typeView) 
	{
		views.add(commandResult, typeView);
	}
	public HtmlView<IType> getView()
	{
		return views;
	}

	public ICommand<IType> find(String[] command, HashMap<String, String> map) throws IllegalCommandException
	{
		if(command == null || command.length<2)
			throw new IllegalCommandException("invalid command");
		
		String [] commandPath = command[1].split("/");  //path recebida
		for(int i = 0; i< list.size(); i++)
		{
			if(commandPath.length == list.get(i).size) // avaliar o tamanho
			{
				String aux = list.get(i).path.substring(0,list.get(i).path.indexOf(" "));
				if(aux.equals(command[0])) // avaliar tipo de comando
				{
					String[] listPath = list.get(i).path.split(" ");
					if(comparePath(listPath[1].split("/"),commandPath,map))
					{
						if(command.length == 3)
							map = Utils.mapper(command[2], map);
						return list.get(i).command;
					}	
				}
			}
		}
		throw new IllegalCommandException("Invalid command");
	}

	public boolean comparePath(String [] listPath, String [] commandPath, HashMap<String,String> map) throws IllegalCommandException
	{
		if(listPath.length != commandPath.length)
			return false;	
		for(int i = 0; i<listPath.length; i++)
		{
			if(!listPath[i].equals(commandPath[i]))
			{
				if(!listPath[i].contains("{"))
					return false;
				else{
					String key = listPath[i].substring(1,listPath[i].length()-1);
					if(key.equals("location"))
					{
						commandPath[i] = Utils.locationTransformer(commandPath[i]);
					}
					map.put(key, commandPath[i]);
				}
			}
		}
		return true;
	}

	public void printCommands() {
		for (int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).path);
		}	
	}
	public  void executeCommand(String [] command) throws Exception{
		if (command[0].equals("OPTION"))
			printCommands();
		else if(command[0].contains("LISTEN"))
			startServer(command);
		else if(command[0].contains("EXIT")){
			if(activeServer)
				server.stopServer();
			System.exit(0);
		}
		else{
			HashMap <String,String> map = new HashMap<String, String>(); 
	
			ICommand<IType> cmd = find(command,map);
			ICommandResult<IType> result = cmd.execute(map);
			Output.Print(result, map, this);
		}
	}
	
	private  void startServer(String [] command) throws Exception
	{
		if(!activeServer){
			HashMap <String,String> map = new HashMap<String, String>(); 
			map = Utils.mapper(command[2], map);
			Integer port;
			try{
				port = Integer.parseInt(map.get("port"));
			}
			catch(NumberFormatException e){
				throw new IllegalCommandException("invalid Port number");
			}
			server = new ServerHTTP(this, port);
			server.initServer();
			activeServer = true;
		}
		else
			System.out.println("There is already an instance of Http server");
	}
}