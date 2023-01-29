package com.epam.donetc.restaurant.database.filter;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.filters.AdminFilter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

public class AdminFilterTest {

    @Test
    public void doFilter_shouldRedirectToLoginPage_whenUserIsNull() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest .class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse .class);
        FilterChain chain = Mockito.mock(FilterChain .class);
        HttpSession session = Mockito.mock(HttpSession .class);
        Mockito.when(request .getSession()).thenReturn(session);
        Mockito.when(session .getAttribute("user")).thenReturn(null);
        String expectedUrl = request.getContextPath()+"/controller?command=login";
        AdminFilter filter = new AdminFilter();
        filter .doFilter(request , response , chain );
        Mockito.verify (response).sendRedirect(expectedUrl);

    }
}