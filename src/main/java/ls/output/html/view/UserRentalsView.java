package ls.output.html.view;

import java.util.HashMap;

import ls.commands.result.UserRentalsResult;
import ls.db.Rental;
import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class UserRentalsView extends HtmlPage implements ITypeView {

	

	public UserRentalsView(UserRentalsResult result, HashMap<String, String> map) {
		super("Rentals",h1(text("Rentals of "+map.get("username"))), 
				result.getArrayList().size() ==0 ? h3(text("No Rentals found")):text(""),
				rentals(result),
				goBack(ofUser(map.get("username")),map.get("username")),
				goInit()
			);
	}

	private static Writable rentals(UserRentalsResult result) {
		if(result.getArrayList().size() == 0)
			return text("");
		int style = 150 * 4;
		HtmlElem table = new HtmlElem("table style=\"width:"+style+"px\" border=\"1\"");
		table.withContent(tr(th(text("Property"),
				th(text("Year")),th(text("Cw")),th(text("Status")))));

		for(Rental rental : result.getArrayList())
		{
			table.withContent(
					tr(
							td(text(rental.property.pid)),

							td(a(ofYear(rental), rental.year)),
							td(a(ofCw(rental),rental.cw)),
							td(text(rental.status))
							));
		}
		return table;
	}

	

	private static String ofCw(Rental rental) {
		return String.format("/properties/%d/rentals/%d/%d", rental.property.pid, rental.year,rental.cw);
	}

	private static String ofYear(Rental rental) {
		return String.format("/properties/%d/rentals/%d", rental.property.pid,rental.year);
	}

	
	private static String ofUser(String username) {
		return String.format("/users/%s", username);
	}

	

	

}
