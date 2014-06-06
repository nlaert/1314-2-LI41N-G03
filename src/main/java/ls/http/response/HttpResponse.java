package ls.http.response;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class HttpResponse {

    private final HttpStatusCode _status;
    private final HttpContent _body;
//    private final String _outputHTML;
    private final Charset _charset = Charset.forName("UTF-8");
    private final Map<String,String> _headers = new HashMap<String,String>();

    public HttpResponse(HttpStatusCode status) {
        this(status,null);
    }
    
    public HttpResponse(HttpStatusCode status, HttpContent body) {        
        _status = status;
        _body = body;
    }
//    public HttpResponse(HttpStatusCode status, String outputHTML) {        
//        _status = status;
//        _outputHTML = outputHTML;
//    }
   

	public HttpResponse withHeader(String name, String value) {
        _headers.put(name, value);
        return this;
    }
    
    public void send(HttpServletResponse resp) throws IOException {
    	for(Map.Entry<String, String> entry : _headers.entrySet()){
    		resp.addHeader(entry.getKey(), entry.getValue());
    	}
    	

        if(_body == null) {
            sendWithoutBody(resp);
        }else {
            sendWithBufferedBody(resp);
        }
    }
        
    private void sendWithoutBody(HttpServletResponse resp) throws IOException {
        resp.setStatus(_status.valueOf());
        
        resp.setHeader("WWW-Authenticate: Basic realm", "Rental");
    	 
        
    }
    
    private void sendWithBufferedBody(HttpServletResponse resp) throws IOException {
    	
    	ByteArrayOutputStream _os = new ByteArrayOutputStream();
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(_os, _charset))){
        	_body.writeTo(writer);
//        	writer.write(_outputHTML);
        }
        byte[] bytes = _os.toByteArray();
        resp.setStatus(_status.valueOf());
//        String ctype = String.format("%s;charset=%s", "text/html", _charset.name());  
        String ctype = String.format("%s;charset=%s",_body.getMediaType(), _charset.name());                
        resp.setContentType(ctype);
        resp.setContentLength(bytes.length);
        OutputStream ros = resp.getOutputStream();
        ros.write(bytes);
        ros.close();        
    }
}
