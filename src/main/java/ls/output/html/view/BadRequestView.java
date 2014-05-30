package ls.output.html.view;

import ls.output.html.HtmlPage;

public class BadRequestView extends HtmlPage implements ITypeView{

	public BadRequestView() {
		super("Page not found",
				h1(text("Page not found")),
				goInit()
				);
	}

}
