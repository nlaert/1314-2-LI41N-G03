package ls.output.html.view;

import java.util.HashMap;

import ls.commands.result.PropertyDetailsResult;
import ls.db.Property;
import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class PropertyDetailsView extends HtmlPage implements ITypeView{

	public PropertyDetailsView(PropertyDetailsResult result, HashMap<String, String> map) {
		super("Property", 
				h1(text("Details of property with ID: "+Integer.toString(result.getArrayList().get(0).pid ))),
				propertiesItemsList(result),
				rentals(result),
				goBack("/properties", "Propriedades"),
				goInit()
			);
	}

	private static Writable propertiesItemsList(PropertyDetailsResult result) {
		HtmlElem ul = new HtmlElem("ul");
		for(Property property : result.getArrayList())
		{
			ul.withContent(li(text("pid: " + property.pid), 
					li(text("type: " + property.type), 
					li(text("price: " + property.price), 
					li(text("location: " + property.location),
					li(text("description: " + property.description), 
					li(a(ofOwner(property),property.owner.username))))))));
		}
		return ul;
	}
	
	private static Writable rentals(PropertyDetailsResult result) {
		HtmlElem h3 = new HtmlElem("h3");
		h3.withContent(
				li(a(ofPidRental(result.getArrayList().get(0)),"Rentals")));
		return h3;
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
