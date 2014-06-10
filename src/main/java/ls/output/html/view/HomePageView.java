package ls.output.html.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class HomePageView extends HtmlPage implements ITypeView{

	public HomePageView() throws UnsupportedEncodingException
	{
		super("Home Page Rentals",
				h1(text("Home Page Rentals")),
				homePageItems(),
				text(URLDecoder.decode("Projecto de João e Nick", "UTF-8"))
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
