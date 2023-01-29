//package com.epam.donetc.restaurant.database;
//
//import com.epam.donetc.restaurant.сontroller.CommandFactory;
//import com.epam.donetc.restaurant.сontroller.FrontController;
//import com.epam.donetc.restaurant.сontroller.ICommand;
//import com.epam.donetc.restaurant.сontroller.command.get.LoginPageCommandGet;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mockito;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//import static org.mockito.Mockito.mock;
//
//public class FrontControllerTest {
//
//    @Test
//    public void testFrontController(){
//        // mock servlet request
//        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
//        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
//
//        // mock command
//        ICommand mockCommand = Mockito.mock(ICommand.class);
//        // stub command factory
//        Mockito.when(CommandFactory.getCommand(mockRequest)).thenReturn(mockCommand);
//        // stub command execute method
//        Mockito.when(mockCommand.execute(mockRequest, mockResponse)).thenReturn("/controller?command=");
//
//        // create instance of class under test
//        FrontController frontController = new FrontController();
//
//        // test doGet
//        Mockito.when(mockRequest.getMethod()).thenReturn("GET");
//        try {
//            frontController.doGet(mockRequest, mockResponse);
//            // verify request dispatcher was called with the expected URL
//            ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
//            Mockito.verify(mockRequest).getRequestDispatcher(urlCaptor.capture());
//            assertEquals("test_url.jsp", urlCaptor.getValue());
//        } catch (ServletException | IOException e) {
//            fail("Unexpected exception");
//        }
//
//        // test doPost
//        Mockito.when(mockRequest.getMethod()).thenReturn("POST");
//        try {
//            frontController.doPost(mockRequest, mockResponse);
//            // verify request dispatcher was called with the expected URL
//            ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
//            Mockito.verify(mockResponse).sendRedirect(urlCaptor.capture());
//            assertEquals("test_url", urlCaptor.getValue());
//        } catch (ServletException | IOException e) {
//            fail("Unexpected exception");
//        }
//
//    }
//
//}
