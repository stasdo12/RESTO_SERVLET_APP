package com.epam.donetc.restaurant.servlets;

import com.epam.donetc.restaurant.service.DishService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-dish")
public class AddDishMangerServlet extends HttpServlet  {

    DishService dishService = new DishService();
    private static final Logger log = LogManager.getLogger(CartServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsp/add-dish.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String name = request.getParameter("newName");
        int prise = Integer.parseInt(request.getParameter("newPrise"));
        int weight = Integer.parseInt(request.getParameter("newWeight"));
        int category = Integer.parseInt(request.getParameter("newCategory"));
        String desc = request.getParameter("newDesc");
        log.trace(" newPrise== " + prise + " newWeight " + weight + " newCategory " + category);
        dishService.addDish(name, prise, weight, category, desc);
        resp.sendRedirect(request.getContextPath() + "/add-dish");
    }
}
