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
				usersItemsTable(result),
				h1(text("Post Users")),
				tablePostUser(),
				//postUser(),
				goInit()
			);
		
	}
	


	private static Writable postUser() {
		
		return form("POST","/users",
				label("username","username: "),
				textInput("username"),
				br(),
				label("password","password: "),
				textInput("password"),
				br(),
				label("email","email:    \t "),
				textInput("email"),
				br(),
				label("fullname","fullname: "),
				textInput("fullname"),
				br(),
				submitInput("submit")
				);
		
	}
private static Writable tablePostUser() {
		HtmlElem table = new HtmlElem("table");
		table.withContent(
				
				form("POST","/users",
				tr(
				td(label("username","username: ")),
				td(textInput("username"))),
				tr(
				td(label("password","password: ")),
				td(textInput("password"))),
				
				tr(
				td(label("email","email:    \t ")),
				td(textInput("email"))),
	
				tr(
				td(label("fullname","fullname: ")),
				td(textInput("fullname"))),
			
				tr(
				td(submitInput("submit")))
				));
		return table;
		
	}



	private static Writable usersItemsTable(UsersResult result) {
		int style = 150 * result.getArrayList().get(0).size;
		HtmlElem table = new HtmlElem("table style=\"width:"+style+"px\" border=\"1\"");
		table.withContent(tr(th(text("Username"),th(text("email"),th(text("Fullname"))))));
		for(User user : result.getArrayList())
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
