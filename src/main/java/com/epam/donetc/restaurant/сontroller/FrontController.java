package com.epam.donetc.restaurant.сontroller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class FrontController extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = handleRequest(req, resp);
        req.getRequestDispatcher(forward + ".jsp").forward(req, resp);

        System.out.println("get request");
        System.out.println(req.getCharacterEncoding());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = handleRequest(req, resp);
        resp.sendRedirect(redirect);
        System.out.println("post request");
    }

    private String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        ICommand iCommand = CommandFactory.getCommand(req);
        return iCommand.execute(req, resp);

    }
}

