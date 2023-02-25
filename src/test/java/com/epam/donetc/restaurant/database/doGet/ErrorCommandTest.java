package com.epam.donetc.restaurant.database.doGet;

import com.epam.donetc.restaurant.controller.command.get.ErrorPageCommand;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ErrorCommandTest {
    @Test
    public void whenCallDoGetThenServletReturnErrorPage(){
        ErrorPageCommand command = new ErrorPageCommand();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String result = command.execute(request, response);

        assertEquals("WEB-INF/jsp/error", result);
    }
}
