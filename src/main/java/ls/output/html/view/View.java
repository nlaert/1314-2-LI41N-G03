package ls.output.html.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
import ls.exception.AppException;
import ls.output.html.HtmlPage;

public class View<E> {
	
//	private ArrayList<ICommandResult<IType>> results;
//	private ArrayList<ITypeView> views;
	private HashMap<Class, Class> map2;
	
	public View(){
//		results = new ArrayList<ICommandResult<IType>>();
//		views = new ArrayList<ITypeView>();
		map2 = new HashMap<Class, Class>();
//		map2.put(PropertiesResult.class, PropertiesView.class);
		
	}
	
	public void add(Class result, Class view){
		map2.put(result, view);
	}
	
	public HtmlPage getView(ICommandResult<E> result, HashMap<String,String> map) throws AppException
	{
		if (map2.containsKey(result.getClass())){
			Class h = map2.get(result.getClass());
			try {
				Constructor<?> k = h.getConstructor(result.getClass(), HashMap.class);
				return (HtmlPage) k.newInstance(result, map);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new AppException("View not Found!", e);
			}
		}
		return null;
	}

		
	
//	public static <E> HtmlPage getView(ICommandResult<E> result, HashMap<String,String>map)
//	{		
//		if(result instanceof PropertiesRentalsWithDateResult)
//		{
//			return new PropertiesRentalsWithDateView((PropertiesRentalsWithDateResult)result,map);
//		}
//		if(result instanceof PropertiesRentalsResult)
//		{
//			return new PropertiesRentalsView((PropertiesRentalsResult) result,map);
//
//		}
//		if(result instanceof UsersResult)
//		{
//			return new UsersView((UsersResult) result,map);
//		}
//
//		if(result instanceof UserUsernameResult)
//		{
//			return new UserUsernameView((UserUsernameResult) result,map);
//		}
//
//		if(result instanceof PropertyDetailsResult)
//		{
//			return new PropertyDetailsView((PropertyDetailsResult) result,map);
//		}
//
//		if(result instanceof PropertiesResult)
//		{
//			return new PropertiesView((PropertiesResult) result,map);
//		}
//
//		if(result instanceof UserRentalsResult)
//		{
//			return new UserRentalsView((UserRentalsResult) result,map);
//		}
//		if(result instanceof RentalsResult)
//		{
//			return new RentalView((RentalsResult) result, map);
//		}
//		if(result instanceof PropertiesRentalsByYearResult)
//		{
//			return new PropertiesRentalsByYearView((PropertiesRentalsByYearResult) result, map);
//		}
//
//		
//		return null;
//	}
}