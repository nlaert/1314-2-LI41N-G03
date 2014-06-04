package ls.output.html;

import java.io.IOException;
import java.io.Writer;

import ls.http.common.CompositeWritable;
import ls.http.common.Writable;
import ls.http.response.HttpContent;



public class Html implements HttpContent {

    private Writable _content;
    
    protected Html(Writable... cs) {
        _content = new CompositeWritable(cs);
    }
           
    @Override
    public void writeTo(Writer w) throws IOException {
        _content.writeTo(w);        
    }
    
    @Override
    public String getMediaType() {
        return "text/html";
    }
    
    public static Writable text(String s) { return new HtmlText(s);}
    public static Writable text(int i) { return new HtmlText(Integer.toString(i));}
    public static Writable h1(Writable... c) { return new HtmlElem("h1",c);}
    public static Writable h2(Writable... c) { return new HtmlElem("h2",c);}
    public static Writable h3(Writable... c) { return new HtmlElem("h3",c);}
    public static Writable form(String method, String url, Writable... c) {
        return new HtmlElem("form",c)
            .withAttr("method", method)
            .withAttr("action", url);
    }
    public static Writable label(String to, String text) {
        return new HtmlElem("label", new HtmlText(text))
            .withAttr("for", to);
    }
    public static Writable textInput(String name) {
        return new HtmlElem("input")
            .withAttr("type", "text")
            .withAttr("name", name);            
    }
    public static Writable input(String name,String type) {
        return new HtmlElem("input")
            .withAttr("type", type)
            .withAttr("name", name);            
    }
    
    public static Writable ul(Writable... c) {
        return new HtmlElem("ul",c);
    }
    public static Writable li(Writable...c) {
        return new HtmlElem("li",c);
    }
    
    public static Writable table(Writable...c) {
    	return new HtmlElem("table",c);
    }
    
    public static Writable tr(Writable...c) {
    	return new HtmlElem("tr",c);
    }
    
    public static Writable th(Writable...c) {
    	return new HtmlElem("th",c);
    }
    
    public static Writable td(Writable...c) {
    	return new HtmlElem("td",c);
    }
    public static Writable a(String href, String t) {
        return new HtmlElem("a", text(t))
            .withAttr("href", href);
    }
    public static Writable a(String href, int t) {
        return new HtmlElem("a", text(t))
            .withAttr("href", href);
    }
    
    protected static Writable goInit() {
		HtmlElem h3 = new HtmlElem("h3");
		h3.withContent(
				li(a("/","Menu")));
		return h3;
	}
    protected static Writable goBack(String path, String name) {
		HtmlElem h3 = new HtmlElem("h3");
		h3.withContent(
				li(a(path,name)));
		return h3;
	}
    
    public static Writable br()
    {
    	HtmlElem br = new HtmlElem("br");
    	return br;
    }
    
}
