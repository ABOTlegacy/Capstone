package com.tek271.reverseProxy.serv;

import com.tek271.reverseProxy.servlet.ContentType;
import com.tek271.reverseProxy.servlet.ContentUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;

public class PageServiceProxy {
    Map<String, String> map = new HashMap<String, String>();

    public String getPage(String url, HttpEntity entity) throws IOException {
        // Instantiate Variables
        String text = null;
        text = map.get(url);

        // If Null, then cache the object
        if (text == null) {
            ContentType contentType = new ContentType(entity.getContentType(), url);
            text = ContentUtils.getContentText(entity, contentType.charset);
            map.put(url, text);
        }

        // Return Object
        return text;
    }
}
