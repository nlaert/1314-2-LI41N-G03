package ls.output.html.view;

import java.util.HashMap;

import ls.commands.result.PropertiesRentalsWithDateResult;
import ls.db.Rental;
import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class PropertiesRentalsWithDateView extends HtmlPage implements ITypeView {

	public PropertiesRentalsWithDateView(PropertiesRentalsWithDateResult result, HashMap<String, String> map) {
		super("Rentals",h1(text("Rentals")), 
				result.getArrayList().size() ==0 ? h3(text("No Rentals found")):text(""),
				rentals(result),
				goInit()
			);		
	}
	
	
	private static Writable rentals(PropertiesRentalsWithDateResult result) {
		if(result.getArrayList().size() == 0)
			return text("");
		
		
		HtmlElem ul = new HtmlElem("ul");
		Rental rental = result.getArrayList().get(0);
		ul.withContent(
				li(text("Property: "),a(ofPid(rental),rental.property.pid),
				li(text("Renter: "),a(ofUser(rental),rental.renter.username),
				li(text("Year: ")),a(ofYear(rental), rental.year),
				li(text("Cw: "+rental.cw)),
				li(text("Status: " + rental.status)),
				li(text("Reserved Date: "+ rental.reserved_date)),
				li(text("Confirmed Date: "+rental.confirmed_date)))));

		return ul;
	}
	
	
	private static String ofPid(Rental rental) {
		return String.format("/properties/%d/rentals", rental.property.pid);
	}


	private static String ofYear(Rental rental) {
		return String.format("/properties/%d/rentals/%d", rental.property.pid,rental.year);
	}

	private static String ofUser(Rental rental) {
		return String.format("/users/%s/rentals", rental.renter.username);
	}

}
