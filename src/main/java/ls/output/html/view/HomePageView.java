package ls.output.html.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class HomePageView extends HtmlPage implements ITypeView{

	public HomePageView() throws UnsupportedEncodingException
	{
		super("NJ Rentals",
				h1(text("NJ Rentals, Lda")),
				homePageItems(),
				h3(text("Realizado por:")),
				a("http://www.linkedin.com/pub/nick-laert/51/29a/547","35466 - Nick Laert"),
				br(),
				a("http://www.linkedin.com/pub/joao-rodrigues/71/1a7/869",URLDecoder.decode("35392 - João Rodrigues", "UTF-8")),
				br(),
				h3(text("Orientadores:")),
				a("https://www.linkedin.com/in/cguedes/pt","Eng. Carlos Guedes"),
				br(),
				a("https://github.com/fbfreitas","Eng. Filipe Freitas"),
				br(),
				br(),
				b(text(URLDecoder.decode("Projecto para a UC Laboratório de Software do curso LEIC - ISEL 2013/2014 SV", "UTF-8")))
				
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
