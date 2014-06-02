package ls.commands.result;

import java.util.ArrayList;

import ls.db.Property;

public class PropertiesResult implements ICommandResult<Property> {
	
	private ArrayList<Property> properties;

	public PropertiesResult(ArrayList<Property> properties)
	{
		this.properties = properties;
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
