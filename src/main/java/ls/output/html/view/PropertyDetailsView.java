package ls.output.html.view;

import java.util.HashMap;

import ls.commands.result.PropertiesResult;
import ls.commands.result.PropertyDetailsResult;
import ls.db.Property;
import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class PropertyDetailsView extends HtmlPage implements ITypeView{

	public PropertyDetailsView(PropertyDetailsResult result, HashMap<String, String> map) {
		super("Property", 
				h1(text("Properties of pid: "+Integer.toString(result.getArrayList().get(0).pid ))),
				propertiesItems(result),
				rentals(result),
				goBack("/properties", "Propriedades"),
				goInit()
			);
	}

	
	


	private static Writable rentals(PropertyDetailsResult result) {
		HtmlElem h3 = new HtmlElem("h3");
		h3.withContent(
				li(a(ofPidRental(result.getArrayList().get(0)),"Rentals")));
		return h3;
	}


	private static Writable propertiesItems(PropertiesResult result) {
		int nColunas = 5;
		int style = 150 * nColunas;
		HtmlElem table = new HtmlElem("table style=\"width:"+style+"px\" border=\"1\"");
		table.withContent(tr(th(text("pid"),th(text("type"),th(text("price"),th(text("location"),th(text("description"),th(text("Owner")))))))));
		for(Property property : result.getArrayList())
		{
			table.withContent(tr(
					td(text(Integer.toString(property.pid)),td(text(property.type),
							td(text(Integer.toString(property.price)),td(text(property.location),
									td(text(property.description),
											td(a(ofOwner(property),property.owner.username)))))))));
		}
		return table;
	
	}
	
	private static String ofOwner(Property property)
	{
		return String.format("/users/%s", property.owner.username);
	}
	
	private static String ofPidRental(Property property)
	{
		return String.format("/properties/%s/rentals", property.pid);
	}
	
	

}
