package com.epam.donetc.restaurant.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/**
 * This is an encoding filter for the entire application
 *
 * */
@WebFilter(urlPatterns = {"/*"})
public class EncodingFilter implements Filter  {
    @Override
    public void init(FilterConfig config) throws ServletException {
        String encoding = config.getInitParameter("UTF-8");

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
