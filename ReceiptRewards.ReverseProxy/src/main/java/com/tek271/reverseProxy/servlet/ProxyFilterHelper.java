/*
 * This file is part of Tek271 Reverse Proxy Server.
 *
 * Tek271 Reverse Proxy Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Tek271 Reverse Proxy Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Tek271 Reverse Proxy Server.  If not, see http://www.gnu.org/licenses/
 */
package com.tek271.reverseProxy.servlet;

import com.tek271.reverseProxy.model.Mapping;
import com.tek271.reverseProxy.text.UrlMapper;
import com.tek271.reverseProxy.utils.Tuple2;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class ProxyFilterHelper {

    public static boolean isHttp(ServletRequest request, ServletResponse response) {
        return (request instanceof HttpServletRequest) && (response instanceof HttpServletResponse);
    }

    public static Tuple2<Mapping, String> mapUrlProxyToHidden(ServletRequest request) {
        String oldUrl = ((HttpServletRequest)request).getRequestURL().toString();
        return UrlMapper.mapFullUrlProxyToHidden(oldUrl);
    }

    public static HttpEntity getEntity(String newUrl) throws IOException{
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(newUrl);
        HttpResponse r = httpclient.execute(httpget);
        return r.getEntity();
    }

    public static void executeRequest(ServletResponse response, Mapping mapping, String newUrl, HttpEntity entity, String text) throws IOException, ClientProtocolException {
        ContentTranslator contentTranslator = new ContentTranslator(mapping, newUrl);
        contentTranslator.translate(entity, response, text);
    }
}