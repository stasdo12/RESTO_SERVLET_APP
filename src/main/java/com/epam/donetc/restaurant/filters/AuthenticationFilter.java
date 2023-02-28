package com.epam.donetc.restaurant.filters;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if the user is authenticated
        if (!isAuthenticated(httpRequest)) {
            // If not, redirect to the login page
            httpResponse.sendRedirect("/login");
        } else {
            // If authenticated, continue with the request
            chain.doFilter(request, response);
        }
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        // Retrieve the authentication information from the session or request headers
        // and check if it is valid
        // Return true if the user is authenticated, false otherwise
        // Example:
        String authToken = request.getHeader("Authorization");
        if (authToken != null && authToken.equals("myAuthToken")) {
            return true;
        } else {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                return true;
            } else {
                return false;
            }
        }
    }
}