package ls.output.json;

import java.io.IOException;
import java.io.Writer;

import ls.http.common.Writable;

public class JsonText implements Writable{
	  public final String _text;
	    
	    public JsonText(String text) {
	        _text = text;
	    }

	    @Override
	    public void writeTo(Writer w) throws IOException {
	        w.write(_text); 
	    }
}