package ls.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Utils {
	
	/**
	 * 
	 * @param command
	 * @return String[] with 2 positions, the first and second part of String command divided by "/"
	 */
	public static String [] limitator(String command)
	{
		String [] divide = new String[2];
		int first = command.indexOf("/");
		if(first == command.lastIndexOf("/"))
		{
			int aux = command.substring(first+1).indexOf(" ");
			if(aux == -1)
			{
				divide[0] = command;
				divide[1] = "";
			}
			else
			{
				aux = aux + first;
				divide[1] = command.substring(aux +2);
				divide[0] = command.substring(0,aux+1);
			}
				
		}
		else
		{
			int aux = command.substring(first+1).indexOf("/") + first;
			divide[1] = command.substring(aux+1);
			divide[0] = command.substring(0,aux+1);
		}
		return divide;
	}
	
	public static boolean checkAuth(String username, String password, Connection conn) throws SQLException
	{
		PreparedStatement prep = conn.prepareStatement("Select username from Users Where username = ? and password = ?");
		prep.setString(1, username);
		prep.setString(2, password);
		return prep.executeQuery().next();
	}
	
	public static HashMap<String, String> mapper(String parameters)
	{
		if (parameters == null || parameters.equals(""))
			return null;
		HashMap <String, String> dict = new HashMap<String, String>();
		String [] aux = parameters.split("&");
		int equal = 0;
		for (int i = 0; i < aux.length; i++)
		{
			if (aux[i].indexOf('+')>=0)
				aux[i] = aux[i].replace('+', ' ');
			equal = aux[i].indexOf('=');
			dict.put(aux[i].substring(0, equal), aux[i].substring(equal+1));			
		}
		return dict;
	}
	
	public static ArrayList<String> resultSetToArrayList(ResultSet rs) throws SQLException
	{
		int columnCount = rs.getMetaData().getColumnCount();
		ArrayList<String> select = new ArrayList<String>(columnCount);
		while(rs.next())
		{
			StringBuilder aux = new StringBuilder();
			for (int i = 1; i<=columnCount; i++)
				aux.append(rs.getString(i) + "\t");
			select.add(aux.toString());
		}
		return select;
	}
	
	public static <E> void printArrayList(ArrayList<E> list){
		for (int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).toString());
		}
	}
}
