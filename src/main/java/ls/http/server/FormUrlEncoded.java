package ls.http.server;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ls.exception.IllegalCommandException;

import com.sun.net.httpserver.HttpExchange;

public class FormUrlEncoded {

    public static boolean canRetriveFrom(HttpExchange t) {
        List<String> values = t.getRequestHeaders().get("Content-Type");
        return values != null && values.size() == 1 && values.get(0).equals("application/x-www-form-urlencoded");        
    }
    
    // TODO assumes Content-Length present and UTF-8 charset
    public static Map<String,String> retrieveFrom(HttpServletRequest req) throws IOException, IllegalCommandException{
        Map<String, String> map = new HashMap<String,String>();
        byte[] bytes = new byte[req.getContentLength()];
        req.getInputStream().read(bytes);
        String content = new String(bytes);
        String[] pairs = content.split("&");
        for(String pair : pairs) {
            String[] kvp = pair.split("=");
            if(!(kvp.length==2))
            	throw new IllegalCommandException("Empty parameters");
            else
            	map.put(URLDecoder.decode(kvp[0], "UTF-8"),
                    URLDecoder.decode(kvp[1], "UTF-8"));
        }
        return map;
    }
    
}
