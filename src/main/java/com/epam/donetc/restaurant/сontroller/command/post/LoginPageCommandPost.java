package com.epam.donetc.restaurant.сontroller.command.post;

import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.AppException;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.UserService;
import com.epam.donetc.restaurant.servlets.LoginServlet;
import com.epam.donetc.restaurant.сontroller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageCommandPost implements ICommand {
    private static final Logger log = LogManager.getLogger(LoginPageCommandPost.class);
    private final UserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login.isEmpty() || password.isEmpty()){
            return req.getContextPath() + "/controller?command=login_page";
        }
        try{
            User user = userService.logIn(login, password);
            if(user != null){
                req.getSession().setAttribute("user", user);
                if(user.getRoleId() == 1){
                    return  req.getContextPath() + "/controller?command=client_menu";
                } else {
                    return   req.getContextPath() + "/controller?command=manageOrders";
                }
            } else {
                req.setAttribute("error", "true");
                return  req.getContextPath() + "/controller?command=error_page";
            }
        } catch (DBException ex){
            log.error("In Login servlet doPost() ", ex);
            throw new AppException("Login error", ex);
        }
    }
    }

