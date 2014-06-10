package ls.output.html.view;

import java.util.HashMap;

import ls.commands.result.StringResult;
import ls.output.html.HtmlPage;

public class StringView extends HtmlPage {

	
	public StringView(StringResult result, HashMap<String, String> map)
	{
		super("Qualquer coisa",text("Qualquer coisa"));
	}
}
