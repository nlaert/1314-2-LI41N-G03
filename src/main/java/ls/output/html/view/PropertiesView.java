package ls.output.html.view;

import java.util.ArrayList;

import ls.db.Property;
import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class PropertiesView extends HtmlPage{

	public PropertiesView(ArrayList<Property> list) {
		super("Properties", 
				h1(text("Properties")),
				propertiesItems(list)
			);
	}
	public PropertiesView() {
		super("Properties", 
				h1(text("Properties")));
	}
	
	private static Writable propertiesItems(ArrayList<Property> list) {
		HtmlElem ul = new HtmlElem("ul");
		for(Property property : list)
		{
			ul.withContent(
					li(a(of(property),property.toString())));
		}
		return ul;
	
	}
	
	private static String of(Property property)
	{
		return String.format("/properties/details/%d", property.pid);
	}

	

}
