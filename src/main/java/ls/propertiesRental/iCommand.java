package ls.propertiesRental;

import java.sql.SQLException;

public interface iCommand {
	

	public void execute(String command) throws SQLException;
}
