package com.epam.donetc.restaurant.controller;

import com.epam.donetc.restaurant.database.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Controller  class. Implements Front-controller pattern. Chooses action to execute and redirect or forward result.
 * @author Stanislav Donetc
 * @version 1.0
 * */
@WebServlet(name = "FrontController", value = "/controller")
public class FrontController extends HttpServlet  {
    private final Logger log  = LogManager.getLogger(FrontController.class);

    /**
     * Calls and executes action and then forwards requestDispatcher
     * @param req comes from user
     * @param resp comes from user
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String command = req.getParameter("command");
        log.trace("This is command what we used now "  + command  + " this id user" + user);
        String forward = handleRequest(req, resp);
        if (forward != null){
            req.getRequestDispatcher(forward + ".jsp").forward(req, resp);
        }
        log.trace(req.getCharacterEncoding() + " get request");
    }

    /**
     * Calls and executes action and then sendRedirect for PRG pattern implementation
     * @param req comes from user
     * @param resp comes from user
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = handleRequest(req, resp);
        if (redirect != null){
            resp.sendRedirect(redirect);
            log.trace(req.getCharacterEncoding() + " post request");
        }

    }

    private String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        ICommand iCommand = CommandFactory.getCommand(req);
        return iCommand.execute(req, resp);


    }
}

