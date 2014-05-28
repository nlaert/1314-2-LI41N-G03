package ls.output;

import java.util.ArrayList;

import ls.commands.IType;

public class HTML {
	
	public static <E> String htmlify(ArrayList<IType> params)
	{

		if (params==null || params.size()==0)
			return "";
		String[]  columnsNames = params.get(0).getColumNames();
		//String [] columnsNames = params.get(0).split("\t");//params first line contains columns names
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


/*

<html>
    <body>
        
        <table  border="1">
            <tr>
                <th>username</th>
                <th>password</th>
                <th>email</th>
                <th>fullname</th>
            </tr>
            <tr>
                <td>nick</td>
                <td>pass</td> 
                <td>a35466@alunos.isel.pt</td>
                <td>Nick Laert</td>
            </tr>
            <tr>
                <td>joao</td>
                <td>pass</td> 
                <td>a35392@alunos.isel.pt</td>
                <td>Joao Rodrigues</td>
            </tr>
        </table>
    </body>

*/

