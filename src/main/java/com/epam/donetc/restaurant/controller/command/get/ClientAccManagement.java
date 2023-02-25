package com.epam.donetc.restaurant.controller.command.get;

import com.epam.donetc.restaurant.controller.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientAccManagement implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "WEB-INF/jsp/account-management";
    }
}
