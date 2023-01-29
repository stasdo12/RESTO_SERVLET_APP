package com.epam.donetc.restaurant.сontroller.command.get;

import com.epam.donetc.restaurant.сontroller.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InfoCommand implements ICommand  {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "WEB-INF/jsp/info";
    }
}
