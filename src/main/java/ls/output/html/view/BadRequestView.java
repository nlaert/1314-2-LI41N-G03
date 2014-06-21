package ls.output.html.view;

import ls.output.html.HtmlPage;

public class BadRequestView extends HtmlPage implements ITypeView{

	public BadRequestView(String message) {
		super("NJ Rentals",
				h1(text("Bad Request")),
				b(text("Error Message: "+ message)),
				goInit()
				);
	}
	
	public BadRequestView() {
		super("NJ Rentals",
				h1(text("Bad Request")),
				goInit()
				);
	}
	
	

}
