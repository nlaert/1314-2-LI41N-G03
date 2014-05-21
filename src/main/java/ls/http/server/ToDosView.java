package ls.http.server;

import ls.http.common.Writable;
import ls.http.send.HtmlElem;
import ls.http.send.HtmlPage;
import ls.http.todo.ToDo;

public class ToDosView extends HtmlPage{

    public ToDosView(Iterable<ToDo> list){
        super("To Do list",
            h1(text("To Do list")),            
            todoItems(list),
            h1(text("Create new To Do")),
            form("POST", "/todos",
                label("description","Descri??o: "),
                textInput("description")
            )
        );        
    }
    
    private static Writable todoItems(Iterable<ToDo> list) {
        HtmlElem ul = new HtmlElem("ul");
        for(ToDo todo : list){
            ul.withContent(
                li(a(ResolveUrl.of(todo),todo.toString()))
            );
        }
        return ul;
    }    
}
