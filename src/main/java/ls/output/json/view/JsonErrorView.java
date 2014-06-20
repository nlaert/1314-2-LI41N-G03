package ls.output.json.view;

import ls.output.json.JsonPage;
import ls.output.json.JsonText;

public class JsonErrorView extends JsonPage{

	public JsonErrorView(String error)
	{
		super(new JsonText(String.format("{\"error\":\"%s\"}",error)));
	}
	
}
