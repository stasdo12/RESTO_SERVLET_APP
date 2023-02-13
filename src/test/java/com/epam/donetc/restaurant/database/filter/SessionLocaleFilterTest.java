//package com.epam.donetc.restaurant.database.filter;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//
//import com.epam.donetc.restaurant.filters.SessionLocaleFilter;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.*;
//import java.io.IOException;
//
//
//public class SessionLocaleFilterTest {
//    FilterConfig filterConfig = Mockito.mock(FilterConfig.class);
//
//    @Test
//    public void doFilterTest() throws IOException, ServletException {
//        SessionLocaleFilter filter = new SessionLocaleFilter();
//        Mockito.when(filterConfig.getInitParameter("defaultLocale")).thenReturn("en");
//        filter.init(filterConfig);
//        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//        HttpSession session = Mockito.mock(HttpSession.class);
//        Mockito.when(request.getParameter("sessionLocale")).thenReturn("uk");
//        Mockito.when(request.getSession()).thenReturn(session);
//        ServletResponse response = Mockito.mock(ServletResponse.class);
//        FilterChain chain = Mockito.mock(FilterChain.class);
//        filter .doFilter(request, response, chain);
//        Mockito.verify(session);
//
//    }
//}
