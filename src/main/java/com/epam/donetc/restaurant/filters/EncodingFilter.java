package com.epam.donetc.restaurant.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter(urlPatterns = {"/*"})
public class EncodingFilter implements Filter  {
    private String encoding;
    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("UTF-8");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; UTF-8");
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {}
}
