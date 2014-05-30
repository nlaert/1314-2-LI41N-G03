package ls.output.html.view;

import ls.commands.result.UserRentalsResult;
import ls.output.html.HtmlPage;

public class UserRentalsView extends HtmlPage implements ITypeView {

	

	public UserRentalsView(UserRentalsResult result) {
		super("Rentals",h1(text("Rentals")), 
				result.getRentals().size() ==0 ? h3(text("No Rentals found")):text(""),
//				usersItems(users),
//				usersItemsTable(result),
				goInit()
			);
	}

}
