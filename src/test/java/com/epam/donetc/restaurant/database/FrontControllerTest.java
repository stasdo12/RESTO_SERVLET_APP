//package com.epam.donetc.restaurant.database;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//import com.epam.donetc.restaurant.сontroller.CommandFactory;
//import com.epam.donetc.restaurant.сontroller.FrontController;
//import com.epam.donetc.restaurant.сontroller.ICommand;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//public class FrontControllerTest {
//    @Mock
//    HttpServletRequest request;
//    @Mock
//    HttpServletResponse response;
//    @Mock
//    ICommand iCommand;
//    @Mock
//    FrontController frontController;
//
//    @BeforeEach
//    public void setUp() {
//        frontController = new FrontController();
//    }
//
//    @Test
//    public void doGetTest() throws ServletException, IOException {
//        when(iCommand.execute(request, response)).thenReturn("forward");
//        when(CommandFactory.getCommand(request)).thenReturn(iCommand);
//        frontController.doGet(request, response);
//        verify(request).getRequestDispatcher("forward.jsp");
//    }
//
//    @Test
//    public void doPostTest() throws ServletException, IOException {
//        when(iCommand.execute(request, response)).thenReturn("redirect");
//        when(CommandFactory.getCommand(request)).thenReturn(iCommand);
//        frontController.doPost(request, response);
//        verify(response).sendRedirect("redirect");
//    }
//
//    @Test
//    public void handleRequestTest() {
//        when(iCommand.execute(request, response)).thenReturn("redirect");
//        when(CommandFactory.getCommand(request)).thenReturn(iCommand);
//        String result = frontController.handleRequest(request, response);
//        assertEquals("redirect", result);
//    }
//}