package com.epam.donetc.restaurant.сontroller.command.get;

import com.epam.donetc.restaurant.сontroller.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorPageCommand implements ICommand  {
    /**
     * Called from the doGet method in the front controller.
     *
     * @return the error page after trying to display the page
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "WEB-INF/jsp/error";
    }
}
