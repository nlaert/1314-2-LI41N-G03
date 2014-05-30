package ls.output.html.view;

import ls.commands.result.ICommandResult;
import ls.commands.result.PropertiesResult;
import ls.commands.result.RentalsResult;
import ls.commands.result.UsersResult;
import ls.commands.result.UserUsernameResult;
import ls.output.html.HtmlPage;

public class View{

	

	public static  <E> HtmlPage getView(ICommandResult<E> result)
	{
		if(result instanceof UsersResult)
		{
			return new UsersView((UsersResult) result);
		}
		if(result instanceof UserUsernameResult)
		{
			return new UserUsernameView((UserUsernameResult) result);
		}
		if(result instanceof PropertiesResult)
		{
			return new PropertiesView(((PropertiesResult) result).getProperties());
		}
		if(result instanceof RentalsResult)
		{
			return new RentalView(((RentalsResult) result).getRentals());
		}
		return null;
		
	}
}
