package com.epam.donetc.restaurant.сontroller.command.get;

import com.epam.donetc.restaurant.сontroller.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InfoCommand implements ICommand  {

    /**
     * Called from the doGet method in the front controller.
     *
     * @return the info page after trying to display the page
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "WEB-INF/jsp/info";
    }
}
