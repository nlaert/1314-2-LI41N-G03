package ls.output.html.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import ls.commands.result.ICommandResult;
import ls.exception.IllegalCommandException;
import ls.output.html.HtmlPage;

public class HtmlView<E> {
	

	private HashMap<Class<?>, Class<?>> resultsViewsMap;
	

	public HtmlView(){
		resultsViewsMap = new HashMap<Class<?>, Class<?>>();	
	}
	
	public void add(Class<?> result, Class<?> view){
		resultsViewsMap.put(result, view);
	}
	
	public HtmlPage getView(ICommandResult<E> result, HashMap<String,String> map) throws IllegalCommandException
	{
		if (resultsViewsMap.containsKey(result.getClass())){
			Class<?> h = resultsViewsMap.get(result.getClass());
			try {
				Constructor<?> k = h.getConstructor(result.getClass(), HashMap.class);
				return (HtmlPage) k.newInstance(result, map);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new IllegalCommandException("View HTML not Found!");
			}
		}
		return null;
	}
}