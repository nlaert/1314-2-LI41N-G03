package ls.commands;

import java.util.ArrayList;

import Exception.CloseConnectionException;
import Exception.ClosingDataAccessException;
import Exception.CommandsException;


public interface iCommand {
	

	public ArrayList<String> execute(String command) throws ClosingDataAccessException, CommandsException, CloseConnectionException ;
}
