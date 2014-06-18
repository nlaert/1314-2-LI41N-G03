package ls.output.html.view;

import ls.output.html.HtmlPage;

public class InternalServerError extends HtmlPage implements ITypeView{

	public InternalServerError(String message) {
		super("NJ Rentals",
				h1(text("Internal Server Error")),
				b(text("Error Message: "+ message)),
				goInit()
				);
	}
	

}
