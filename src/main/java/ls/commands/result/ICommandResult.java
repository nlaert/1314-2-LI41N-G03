package ls.commands.result;

import java.util.ArrayList;

public interface ICommandResult<IType> {
	public int getSize();
	public ArrayList<IType> getArrayList();
	public String toString();
}
