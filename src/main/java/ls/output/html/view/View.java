package ls.output.html.view;

import java.util.ArrayList;
import java.util.HashMap;

import ls.commands.result.ICommandResult;
import ls.commands.result.PropertiesRentalsByYearResult;
import ls.commands.result.PropertiesRentalsResult;
import ls.commands.result.PropertiesRentalsWithDateResult;
import ls.commands.result.PropertiesResult;
import ls.commands.result.PropertyDetailsResult;
import ls.commands.result.RentalsResult;
import ls.commands.result.UserRentalsResult;
import ls.commands.result.UserUsernameResult;
import ls.commands.result.UsersResult;
import ls.db.IType;
import ls.output.html.HtmlPage;

public class View {
	
	private ArrayList<ICommandResult<IType>> results;
	private ArrayList<ITypeView> views;
	private HashMap<Class<ICommandResult<IType>>, Class<ITypeView>> map2;
	
	public View(){
		results = new ArrayList<ICommandResult<IType>>();
		views = new ArrayList<ITypeView>();
		map2 = new HashMap<Class<ICommandResult<IType>>, Class<ITypeView>>();
		
	}
	
	public void add(Class<ICommandResult<IType>> result, Class<ITypeView> view){
		map2.put(result, view);
	}
	
	public <E> HtmlPage getView(ICommandResult<IType> result)
	{
		for (int i = 0; i < results.size(); i++){
			ICommandResult<IType> r = results.get(i);
			if (result.getClass() == r.getClass())
				return (HtmlPage) views.get(i);
		}
		return null;
	}

		
	
	public static <E> HtmlPage getView(ICommandResult<E> result, HashMap<String,String>map)
	{


		
		if(result instanceof PropertiesRentalsWithDateResult)
		{
			return new PropertiesRentalsWithDateView((PropertiesRentalsWithDateResult)result,map);
		}
		if(result instanceof PropertiesRentalsResult)
		{
			return new PropertiesRentalsView((PropertiesRentalsResult) result,map);

		}
		if(result instanceof UsersResult)
		{
			return new UsersView((UsersResult) result,map);
		}

		if(result instanceof UserUsernameResult)
		{
			return new UserUsernameView((UserUsernameResult) result,map);
		}

		if(result instanceof PropertyDetailsResult)
		{
			return new PropertyDetailsView((PropertyDetailsResult) result,map);
		}

		if(result instanceof PropertiesResult)
		{
			return new PropertiesView((PropertiesResult) result,map);
		}

		if(result instanceof UserRentalsResult)
		{
			return new UserRentalsView((UserRentalsResult) result,map);
		}
		if(result instanceof RentalsResult)
		{
			return new RentalView((RentalsResult) result, map);
		}
		if(result instanceof PropertiesRentalsByYearResult)
		{
			return new PropertiesRentalsByYearView((PropertiesRentalsByYearResult) result, map);
		}

		
		return null;
	}
}