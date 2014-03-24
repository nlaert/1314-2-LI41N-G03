package ls.propertiesRental;

import java.sql.SQLException;

import ls.commands.CommandsException;

public interface iCommand {
	

	public void execute(String command) throws SQLException ;
}
