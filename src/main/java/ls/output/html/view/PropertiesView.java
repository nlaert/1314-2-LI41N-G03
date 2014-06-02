package ls.output.html.view;

import java.util.HashMap;

import ls.commands.result.PropertiesResult;
import ls.db.Property;
import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class PropertiesView extends HtmlPage implements ITypeView{

	public PropertiesView(PropertiesResult result, HashMap<String, String> map) {
		super("Properties", 
				h1(text("All Properties")),
				propertiesItems(result),
				goInit()
			);
	}
	
	
	private static Writable propertiesItems(PropertiesResult result) {
		int nColunas = 5;
		int style = 150 * nColunas;
		HtmlElem table = new HtmlElem("table style=\"width:"+style+"px\" border=\"1\"");
		table.withContent(tr(th(text("pid"),th(text("type"),th(text("price"),th(text("location"),th(text("description"))))))));
		for(Property property : result.getArrayList())
		{
			table.withContent(tr(
					td(a(of(property),Integer.toString(property.pid)),td(text(property.type),td(text(Integer.toString(property.price)),td(text(property.location),td(text(property.description))))))));
		}
		return table;
	
	}
	
	private static String of(Property property)
	{
		return String.format("/properties/details/%d", property.pid);
	}

	

}
