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
	private HashMap<Class, Class> resultsViewsMap;
	
	public View(){
		resultsViewsMap = new HashMap<Class, Class>();	
	}
	
	public void add(Class result, Class view){
		resultsViewsMap.put(result, view);
	}
	
	public HtmlPage getView(ICommandResult<E> result, HashMap<String,String> map) throws AppException
	{
		if (resultsViewsMap.containsKey(result.getClass())){
			Class h = resultsViewsMap.get(result.getClass());
			try {
				Constructor<?> k = h.getConstructor(result.getClass(), HashMap.class);
				return (HtmlPage) k.newInstance(result, map);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new AppException("View not Found!", e);
			}
		}
		return null;
	}
}