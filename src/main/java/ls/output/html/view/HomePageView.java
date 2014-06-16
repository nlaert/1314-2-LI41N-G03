package ls.output.html.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class HomePageView extends HtmlPage implements ITypeView{

	public HomePageView() throws UnsupportedEncodingException
	{
		super("Rental Manager",
				h1(text("Rental Manager")),
				homePageItems(),
				text(URLDecoder.decode("Projecto para a UC Laboratório de Software do curso LEIC - ISEL 2013/2014 SV", "UTF-8")),
				br(),
				text("Realizado por:"),
				br(),
				text("35466 - Nick Laert"),
				br(),
				text(URLDecoder.decode("35392 - João Rodrigues", "UTF-8")));
				
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
