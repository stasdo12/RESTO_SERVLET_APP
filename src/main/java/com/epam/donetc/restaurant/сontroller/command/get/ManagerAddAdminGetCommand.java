package com.epam.donetc.restaurant.сontroller.command.get;

import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.service.UserService;
import com.epam.donetc.restaurant.сontroller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

public class ManagerAddAdminGetCommand implements ICommand {


    private final static Logger log = LogManager.getLogger(ManagerAddAdminGetCommand.class);
    private final UserService userService = new UserService();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<User> users;
        users = userService.getAllUser();
        log.trace(users.size() + " all user who are not a admin");
        req.setAttribute("users", users);
        return "WEB-INF/jsp/add-admin";
    }
}
