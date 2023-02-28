package com.epam.donetc.restaurant.controller.command.post;

import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.AppException;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.UserService;
import com.epam.donetc.restaurant.controller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpPostCommand implements ICommand {

    private final static Logger log = LogManager.getLogger(SignUpPostCommand.class);
    private final UserService userService = new UserService();

    /**
     * Called from doPost method in front-controller. Tries to put user to database.
     * Logs error in case if not able
     *
     * @param req to get users id
     * @return path to redirect to execute Get method through front-controller
     */

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        if(login.isEmpty()|| password.isEmpty()){
            return req.getContextPath() + "/controller?command=sign_up";

        }
        try{
            if(userService.getUserByLogin(login) != null){
                req.setAttribute("err", "true");
                return req.getContextPath() + "/controller?command=sign_up&err=";


            }
            User user = userService.signUp(login, password,email);
            req.getSession().setAttribute("user", user);
        } catch (DBException ex) {
            log.error("In signup servlet doPost() ", ex);
            throw new AppException(ex);
        }
        return req.getContextPath() + "/controller?command=client_menu";
    }
}