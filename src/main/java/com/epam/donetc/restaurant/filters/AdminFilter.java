package com.epam.donetc.restaurant.filters;

import com.epam.donetc.restaurant.database.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This is an administrator permission filter
 * */
@WebFilter( urlPatterns = "/")



public class AdminFilter extends HttpFilter  {

    Logger log = LogManager.getLogger(AdminFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {


        User user = (User) req.getSession().getAttribute("user");
        log.trace(user + " This is user id");
        if (user == null){
            res.sendRedirect(req.getContextPath() + "/controller?command=login");
        }else
            chain.doFilter(req, res);
         }

}
