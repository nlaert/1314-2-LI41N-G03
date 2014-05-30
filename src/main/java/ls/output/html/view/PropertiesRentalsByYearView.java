package ls.output.html.view;

import java.util.HashMap;

import ls.commands.result.PropertiesRentalsByYearResult;
import ls.db.Rental;
import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class PropertiesRentalsByYearView extends HtmlPage implements ITypeView{

	public PropertiesRentalsByYearView(PropertiesRentalsByYearResult result, HashMap<String, String> map) {
		super("Property Rentals by Year", h1(text("Rentals")),
				result.getSize() > 0 ? rentalsItems(result) : ul(text("No Rentals Found")),
				nextYear(result, map),
				previousYear(result, map),
				goInit()
		);
	}
	
	private static Writable rentalsItems(PropertiesRentalsByYearResult result) {
		int nColunas = 7;
		int style = 150 * nColunas;
		HtmlElem table = new HtmlElem("table style=\"width:"+style+"px\" border=\"1\"");
		table.withContent(tr(th(text("Property"),th(text("Renter"),
				th(text("Year")),th(text("Cw")),th(text("Status")),
				th(text("Reserved Date")),th(text("Confirmed Date"))))));
		for(Rental rental : result.getArrayList())
		{
			table.withContent(tr(
					td(a(PropertiesRentalsView.ofProperty(rental), rental.property.pid)),
					td(a(ofUser(rental), rental.renter.username)),
						td(text(rental.year)),
								td(a(PropertiesRentalsView.ofWeek(rental), rental.cw)),
								td(text(rental.status)),
								td(text(rental.reserved_date)),
								td(text(rental.confirmed_date))));
		}
		return table;
	}

	private static String ofUser(Rental rental) {
		return String.format("/users/%s/rentals", rental.renter.username);
	}

	private static Writable previousYear(PropertiesRentalsByYearResult result, HashMap<String, String> map) {
//		if (result.getSize() == 0)
//			return h3(text("no Prop"));
		String uri = String.format("/properties/%d/rentals/%d", Integer.parseInt(map.get("pid")), Integer.parseInt(map.get("year"))-1);
		HtmlElem h3 = new HtmlElem("h3");
		h3.withContent(
				li(a(uri,"Previous Year")));
		return h3;
	}

	private static Writable nextYear(PropertiesRentalsByYearResult result, HashMap<String, String> map) {
//		if (result.getSize() == 0)
//			return h3(text("no Prop"));
		String uri = String.format("/properties/%d/rentals/%d", Integer.parseInt(map.get("pid")), Integer.parseInt(map.get("year"))+1);
		HtmlElem h3 = new HtmlElem("h3");
		h3.withContent(
				li(a(uri,"Next Year")));
		return h3;
	}

}
