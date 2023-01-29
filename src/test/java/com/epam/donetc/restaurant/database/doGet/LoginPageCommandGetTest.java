package com.epam.donetc.restaurant.database.doGet;
import com.epam.donetc.restaurant.сontroller.command.get.LoginPageCommandGet;
import org.junit.jupiter.api.Test;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginPageCommandGetTest {

   @Test
    public void whenCallDoGetThenServletReturnLoginPage() throws ServletException, IOException{
       final LoginPageCommandGet command = new LoginPageCommandGet();

       final HttpServletRequest request = mock(HttpServletRequest.class);
       final HttpServletResponse response = mock(HttpServletResponse.class);

       String result = command.execute(request, response);


      assertEquals("WEB-INF/jsp/login", result);
   }

}
