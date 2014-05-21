package ls.http.server;

import ls.http.send.HtmlPage;
import ls.http.todo.ToDo;

public class ToDoView extends HtmlPage {
    
    public ToDoView(ToDo td){
        super("To Do",
            h1(text("To Do")),
            h3(text("Descri??o: "+td.getDescription())),
            a(ResolveUrl.ofToDoCollection(), "Collection")            
        );        
    }
}
