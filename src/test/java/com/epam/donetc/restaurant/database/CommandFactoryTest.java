package com.epam.donetc.restaurant.database;

import com.epam.donetc.restaurant.сontroller.CommandEnum;
import com.epam.donetc.restaurant.сontroller.CommandFactory;
import com.epam.donetc.restaurant.сontroller.ICommand;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandFactoryTest {
    @Test
    public void testGetCommand(){
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("command")).thenReturn("info");

        ICommand command = CommandFactory.getCommand(req);

        assertEquals(command, CommandEnum.info.getCommand());
    }
}
