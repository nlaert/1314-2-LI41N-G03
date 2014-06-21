package ls.output.html.view;

import ls.output.html.HtmlPage;

public class InternalServerErrorView extends HtmlPage implements ITypeView{

	public InternalServerErrorView(String message) {
		super("NJ Rentals",
				h1(text("Internal Server Error")),
				b(text("Error Message: "+ message)),
				goInit()
				);
	}
	

}
