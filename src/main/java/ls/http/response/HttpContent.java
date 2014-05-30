package ls.http.response;

import ls.http.common.Writable;

public interface HttpContent extends Writable {
    String getMediaType();    
}
