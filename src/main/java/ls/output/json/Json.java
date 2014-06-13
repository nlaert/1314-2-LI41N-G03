package ls.output.json;

import java.io.IOException;
import java.io.Writer;

import ls.http.common.CompositeWritable;
import ls.http.common.Writable;
import ls.http.response.HttpContent;
import ls.output.Page;

public class Json implements Page{

	private Writable _content;
	
	protected Json (Writable... cs)
	{
		 _content = new CompositeWritable(cs);
	}
	@Override
	public void writeTo(Writer w) throws IOException {
		 _content.writeTo(w); 
		
	}

	@Override
	public String getMediaType() {
		return "application/json";
	}
	

}
