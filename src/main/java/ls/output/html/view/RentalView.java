package ls.output.html.view;

import java.util.HashMap;

import ls.commands.result.RentalsResult;
import ls.db.Rental;
import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;


public class RentalView extends HtmlPage {

	public RentalView(RentalsResult result, HashMap<String, String> map) {
		super("Rentals", 
				h1(text("Rentals")),
				rentalsItems(result)
			);
	}
	private static Writable rentalsItems(RentalsResult result) {
		HtmlElem ul = new HtmlElem("ul");
		for(Rental rental : result.getRentals())
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
