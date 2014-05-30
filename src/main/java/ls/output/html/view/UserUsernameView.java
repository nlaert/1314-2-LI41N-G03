package ls.output.html.view;

import ls.commands.result.UserUsernameResult;
import ls.db.Property;
import ls.db.User;
import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class UserUsernameView extends HtmlPage implements ITypeView{ 
	
	

	public UserUsernameView(UserUsernameResult result) {
		super("Users", 
				h1(text("Details of "+result.getUsers().get(0).username)),
				userItemList(result),
				h1(text("Properties of "+result.getUsers().get(0).username)),
				result.getProperties().size() > 0 ? userPropertiesTable(result):(ul(text("Nao existe propriedades"))),
				rentals(result),
				goBack("/users", "Users"),
				goInit()
				
			);
	}

	private static Writable rentals(UserUsernameResult result) {
		HtmlElem h3 = new HtmlElem("h3");
		h3.withContent(
				li(a(ofUserRental(result.getUsers().get(0)),"Rentals")));
		return h3;
	}

	private static Writable userPropertiesTable(UserUsernameResult result) {
		int nColunas = 5;
		int style = 150 * nColunas;
		HtmlElem table = new HtmlElem("table style=\"width:"+style+"px\" border=\"1\"");
		table.withContent(tr(th(text("pid"),th(text("type"),th(text("price"),th(text("location"),th(text("description"))))))));
		for(Property property : result.getProperties())
		{
			table.withContent(tr(
					td(a(of(property),Integer.toString(property.pid)),td(text(property.type),td(text(Integer.toString(property.price)),td(text(property.location),td(text(property.description))))))));
		}
		return table;

	}

	private static Writable userItemList(UserUsernameResult result) {
		HtmlElem ul = new HtmlElem("ul");
		for(User user : result.getUsers())
		{
			ul.withContent(
					li(text("Username: "+user.username),li(text("Password: "+user.password),li(text("Email: "+user.email),li(text("Fullname: "+user.fullName))))));
		}
		return ul;
	}
	
	private static String of(Property property)
	{
		return String.format("/properties/details/%s", property.pid);
	}
	private static String ofUserRental(User user)
	{
		return String.format("/users/%s/rentals", user.username);
	}
	
}
