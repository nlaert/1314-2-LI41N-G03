package ls.output.html.view;

import java.util.HashMap;

import ls.commands.result.UsersResult;
import ls.db.User;
import ls.http.common.Writable;
import ls.output.html.HtmlElem;
import ls.output.html.HtmlPage;

public class UsersView  extends HtmlPage implements ITypeView{

	
	
	public UsersView(UsersResult result, HashMap<String, String> map) {
		super("Users", 
				h1(text("All Users")),
//				usersItems(users),
				usersItemsTable(result),
				goInit()
			);
		
	}
	


	private static Writable usersItemsTable(UsersResult result) {
		int style = 150 * result.getUsers().get(0).size;
		HtmlElem table = new HtmlElem("table style=\"width:"+style+"px\" border=\"1\"");
		table.withContent(tr(th(text("Username"),th(text("email"),th(text("Fullname"))))));
		for(User user : result.getUsers())
		{
			table.withContent(tr(
					td(a(of(user),user.username),td(text(user.email),td(text(user.fullName))))));
		}
		return table;
	
	}
	
	
	private static String of(User user)
	{
		return String.format("/users/%s", user.username);
	}

	
	
	
}
