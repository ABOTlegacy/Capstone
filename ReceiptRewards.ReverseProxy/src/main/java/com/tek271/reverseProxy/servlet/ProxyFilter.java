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
import com.tek271.reverseProxy.serv.EntityServiceProxy;
import com.tek271.reverseProxy.serv.PageServiceProxy;
import com.tek271.reverseProxy.utils.Tuple2;
import java.io.IOException;
import javax.servlet.*;
import org.apache.http.*;

public class ProxyFilter implements Filter {
    PageServiceProxy pageService = new PageServiceProxy();
    EntityServiceProxy entityService = new EntityServiceProxy();

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (! ProxyFilterHelper.isHttp(request, response)) {
            return;
        }

        Tuple2<Mapping, String> mapped = ProxyFilterHelper.mapUrlProxyToHidden(request);
        if (mapped.isNull()) {
            chain.doFilter(request, response);
            return;
        }

        // Check for Proxy
        HttpEntity entity = entityService.getPage(mapped.e2);
        String text = pageService.getPage(mapped.e2, entity);
        ProxyFilterHelper.executeRequest(response, mapped.e1, mapped.e2, entity, text);
    }
}