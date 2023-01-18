package com.epam.donetc.restaurant.Controller.command.get;

import com.epam.donetc.restaurant.Controller.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerAddDishCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "WEB-INF/jsp/add-dish";
    }
}
