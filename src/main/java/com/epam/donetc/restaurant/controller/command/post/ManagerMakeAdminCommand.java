package com.epam.donetc.restaurant.controller.command.post;

import com.epam.donetc.restaurant.service.UserService;
import com.epam.donetc.restaurant.controller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerMakeAdminCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(ManagerMakeAdminCommand.class);
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int userId = Integer.parseInt(req.getParameter("id"));
        if(userId != 0){
            userService.changeUserRoleId(userId);
            log.trace(userId + " new user id");
        }
        return req.getContextPath()+ "/controller?command=add_admin";
    }
}
