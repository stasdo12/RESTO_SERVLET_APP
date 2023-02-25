package com.epam.donetc.restaurant.controller.command.get;

import com.epam.donetc.restaurant.controller.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class ManagerAddDishCommand implements ICommand  {

    /**
     * Called from the doGet method in the front controller.
     *
     * @return the addDish page after trying to display the page
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "WEB-INF/jsp/add-dish";
    }
}
