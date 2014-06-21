package ls.output.html.view;

import ls.output.html.HtmlPage;

public class NotFoundView extends HtmlPage implements ITypeView{

	public NotFoundView() {
		super("NJ Rentals",
				h1(text("Not Found")),
				goInit()
				);
	}
	

}
