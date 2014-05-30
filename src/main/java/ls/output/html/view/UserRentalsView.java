package ls.output.html.view;

import ls.commands.result.UserRentalsResult;
import ls.db.Rental;
import ls.db.User;
import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class UserRentalsView extends HtmlPage implements ITypeView {

	

	public UserRentalsView(UserRentalsResult result) {
		super("Rentals",h1(text("Rentals")), 
				result.getRentals().size() ==0 ? h3(text("No Rentals found")):text(""),
				rentals(result),
//				usersItems(users),
//				usersItemsTable(result),
				goInit()
			);
	}

	private static Writable rentals(UserRentalsResult result) {
		if(result.getRentals().size() == 0)
			return text("");
		int style = 150 * 7;
		HtmlElem table = new HtmlElem("table style=\"width:"+style+"px\" border=\"1\"");
		table.withContent(tr(th(text("Property"),th(text("Renter"),
				th(text("Year")),th(text("Cw")),th(text("Status")),
				th(text("Reserved Date")),th(text("Confirmed Date"))))));
		for(Rental rental : result.getRentals())
		{
			table.withContent(
				tr(
					td(text(rental.property.pid)),
						td(a(ofUser(rental), rental.renter.username)),
							td(a(ofYear(rental), rental.year)),
									td(text(rental.cw)),
									td(text(rental.status)),
									td(text(rental.reserved_date)),
									td(text(rental.confirmed_date))));
		}
		return table;
	}

	

	private static String ofYear(Rental rental) {
		return String.format("/properties/%d/rentals/%d", rental.property.pid,rental.year);
	}

	private static String ofUser(Rental rental) {
		return String.format("/users/%s", rental.renter.username);
	}

	

	

}
