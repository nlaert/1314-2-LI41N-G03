package ls.output.html;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import ls.commands.result.ICommandResult;
import ls.db.IType;
import ls.http.common.Writable;
import ls.http.response.HttpContent;

public class HTMLantigo   {
	
	
	
	public static <E> String hhtmlify(ArrayList<IType> params)
	{

		if (params==null || params.size()==0)
			return "";
		String[]  columnsNames = params.get(0).getColumNames();
		StringBuilder result = new StringBuilder();
		int style = 150 * columnsNames.length;
		result.append("<html><body><table style =\"width:"+style+"px\" border =\"1\">");
		
		result.append("<tr>");
		for(int j = 0; j<columnsNames.length;j++)
		{
				result.append("<th>");
				result.append(columnsNames[j]);
				result.append("</th>");
		}
		result.append("</tr>");
		
		for(int i = 0;i<params.size();i++)
		{
			String[] row = params.get(i).toString().split("\t");
			
			for(int j = 0; j<row.length;j++)
			{
				
					result.append("<td>");
					result.append(row[j]);
					result.append("</td>");
				
			}
			result.append("</tr>");
		
		}
		
		result.append("</table></body></html>");
		return result.toString();
	}
	
	



	



}

