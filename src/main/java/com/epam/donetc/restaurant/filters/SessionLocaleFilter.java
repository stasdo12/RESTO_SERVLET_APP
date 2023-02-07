package com.epam.donetc.restaurant.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * This is an encoding locale for the entire application
 *
 * */
@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter  {


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String sessionLocale = req.getParameter("sessionLocale");

        if (sessionLocale != null) {
            req.getSession().setAttribute("lang", sessionLocale);
        }

        chain.doFilter(req, resp);
    }

    public void destroy() {}

    public void init(FilterConfig arg0) throws ServletException {
        String defaultLocale = arg0.getInitParameter("defaultLocale");//delete
    }

}