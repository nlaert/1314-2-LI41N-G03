package ls.commands.result;

import java.util.ArrayList;

import ls.db.Property;

public class PropertiesResult implements ICommandResult<Property> {
	
	private ArrayList<Property> properties;

	public PropertiesResult(ArrayList<Property> properties)
	{
		this.properties = properties;
	}
	
	
<<<<<<< HEAD
=======
	public PropertiesResult() {
		// TODO Auto-generated constructor stub
	}


	public void setProperties(ArrayList<Property> properties)
	{
		this.properties = properties;
	}
>>>>>>> FETCH_HEAD
	public ArrayList<Property> getProperties()
	{
		return properties;
	}

	@Override
	public int getSize() {
		return properties.size();
	}

	@Override
	public ArrayList<Property> getArrayList() {
		return properties;
	}
	
}
