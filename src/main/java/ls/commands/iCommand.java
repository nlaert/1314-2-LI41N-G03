package ls.commands;

import Exception.ClosingDataAccessException;
import Exception.CommandsException;


public interface iCommand {
	

	public void execute(String command) throws ClosingDataAccessException, CommandsException ;
}
