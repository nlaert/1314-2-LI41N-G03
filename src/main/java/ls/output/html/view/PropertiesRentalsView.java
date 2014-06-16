package ls.output.html.view;

import java.util.HashMap;

import ls.commands.result.PropertiesRentalsResult;
import ls.db.Rental;
import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class PropertiesRentalsView extends HtmlPage implements ITypeView{

	public PropertiesRentalsView(PropertiesRentalsResult result, HashMap<String, String> map) {
		super("Property Rentals", h1(text("Rentals")),
				rentalsItems(result),
				h1(text("Rent this Property")),
				postRental(),
				goInit()
		);
	}
	
	private static Writable postRental() {
		HtmlElem table = new HtmlElem("table");
		table.withContent(
				
				form("POST","/properties/{pid}/rentals",
				tr(
				td(label("year","year:")),
				td(textInput("year"))),
				tr(
				td(label("cw","week:")),
				td(textInput("cw"))),			
				tr(
				td(input("submit", "submit")))
				));
		return table;
	}

	private static Writable rentalsItems(PropertiesRentalsResult result) {
		if(result.getSize() == 0)
			return text("No rentals found");
		int nColunas = 7;
		int style = 150 * nColunas;
		HtmlElem table = new HtmlElem("table style=\"width:"+style+"px\" border=\"1\"");
		table.withContent(tr(th(text("Property"),th(text("Renter"),
				th(text("Year")),th(text("Cw")),th(text("Status")),
				th(text("Reserved Date")),th(text("Confirmed Date"))))));
		for(Rental rental : result.getArrayList())
		{
			table.withContent(tr(
					td(a(ofProperty(rental), rental.property.pid)),
					td(text(rental.renter.username)),
						td(a(ofYear(rental), rental.year)),
								td(a(ofWeek(rental), rental.cw)),
								td(text(rental.status)),
								td(text(rental.reserved_date)),
								td(text(rental.confirmed_date))));
		}
		return table;
	}

	public static String ofWeek(Rental rental) {
		return String.format("/properties/%d/rentals/%d/%d", rental.property.pid, rental.year, rental.cw);
	}

	public static String ofYear(Rental rental) {
		return String.format("/properties/%d/rentals/%d", rental.property.pid, rental.year);
	}

	public static String ofProperty(Rental rental) {
		return String.format("/properties/details/%d", rental.property.pid);
	}

}

//[property],[renter],[year],[cw],[status],[reserved_date],[confirmed_date]
