package ls.output.html.view;

import ls.commands.result.ICommandResult;
import ls.commands.result.PropertiesResult;
import ls.commands.result.RentalsResult;
import ls.commands.result.UserUsernameResult;
import ls.commands.result.UsersResult;
import ls.output.html.HtmlPage;

public class View{

//	private ArrayList<ICommandResult<IType>> results;
//	private ArrayList<ITypeView> views;
//	
//	public View(){
//		results = new ArrayList<ICommandResult<IType>>();
//		views = new ArrayList<ITypeView>();
//	}
//	
//	public void add(ICommandResult<IType> result, ITypeView view){
//		results.add(result);
//		views.add(view);
//	}
	
//	public <E> HtmlPage getView(ICommandResult<IType> result)
//	{
//		for (int i = 0; i < results.size(); i++){
//			ICommandResult<IType> r = results.get(i);
//			if (result.getClass() == r.getClass())
//				return (HtmlPage) views.get(i);
//		}
//		return null;
//	}
	public static <E> HtmlPage getView(ICommandResult<E> result)
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
