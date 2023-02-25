package com.epam.donetc.restaurant.database.doGet;

import com.epam.donetc.restaurant.controller.command.get.ManagerAddDishCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddDishCommandTest {
    @Test
    public void whenCallDoGetThenServletReturnAddDishPage(){
        ManagerAddDishCommand command = new ManagerAddDishCommand();

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);


        String result = command.execute(request, response);

        assertEquals("WEB-INF/jsp/add-dish", result);


    }
}
