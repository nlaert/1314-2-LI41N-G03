package ls.output.html.view;

import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class HomePageView extends HtmlPage implements ITypeView{

	public HomePageView()
	{
		super("Home Page Rentals",
				h1(text("Home Page Rentals")),
				homePageItems(),
				text("Projecto de João e Nick")
				);
				
	}
	
	private static Writable homePageItems() {
		HtmlElem ul = new HtmlElem("ul");
		ul.withContent(li(a(ofUsers(),"Users")));
		ul.withContent(li(a(ofProperties(),"Properties")));
		return ul;
	
	}
	private static String ofUsers()
	{
		return String.format("/users");
	}
	private static String ofProperties()
	{
		return String.format("/properties");
	}

}
