package com.epam.donetc.restaurant.servlets;

import com.epam.donetc.restaurant.service.DishService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/delete")
public class DeleteDishManagerServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CartServlet.class);
    DishService dishService = new DishService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int dishId = Integer.parseInt(req.getParameter("id"));
        try {
            if (dishId != 0) {
                dishService.deleteDish(dishId);
            } else {
                resp.getWriter().println("ERROR");
            }
        } catch (IOException e) {
            log.error("In cart servlet doPost() ", e);
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/change-dish");
    }
}
