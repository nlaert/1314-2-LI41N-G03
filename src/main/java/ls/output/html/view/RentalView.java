package ls.output.html.view;

import java.util.ArrayList;

import ls.db.Rental;
import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;


public class RentalView extends HtmlPage {

	public RentalView(ArrayList<Rental> list) {
		super("Rentals", 
				h1(text("Rentals")),
				rentalsItems(list)
			);
	}
	private static Writable rentalsItems(ArrayList<Rental> list) {
		HtmlElem ul = new HtmlElem("ul");
		for(Rental rental : list)
		{
			ul.withContent(
					li(a(of(rental),rental.toString())));
		}
		return ul;
	
	}
	
	private static String of(Rental rental)
	{
		return String.format("/properties/%d/rentals",rental.property.pid);
	}


}
