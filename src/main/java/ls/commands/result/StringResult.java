package ls.commands.result;

import java.util.ArrayList;

public class StringResult implements ICommandResult<String> {

	private ArrayList<String> result;
	public StringResult(ArrayList<String> result)
	{
		this.result = result;
	}
	


	@Override
	public int getSize() {
		return result.size();
	}

	@Override
	public ArrayList<String> getArrayList() {
		return result;
	}
	
	@Override
	public String toString(){
		return result.toString();
	}
}
