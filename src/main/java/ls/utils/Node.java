package ls.utils;

import ls.commands.iCommand;

public class Node {

	public String path;
	public int size;
	public iCommand command;
	public Node next;
	public Node previous;
	
	public Node(String path, int size, iCommand command)
	{
		this.path = path;
		this.size = size;
		this.command = command;
	}

	public Node() {
		// TODO Auto-generated constructor stub
	}
	
}
