package com.tek271.reverseProxy.serv;

import com.tek271.reverseProxy.servlet.ProxyFilterHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;

public class EntityServiceProxy {
    Map<String, HttpEntity> map = new HashMap<String, HttpEntity>();

    public HttpEntity getPage(String url) throws IOException {
        // Instantiate Variables
        HttpEntity entity = null;
        entity = map.get(url);

        // If Null, then cache the object
        if (entity == null) {
            entity = ProxyFilterHelper.getEntity(url);
            map.put(url, entity);
        }

        // Return Object
        return entity;
    }
}