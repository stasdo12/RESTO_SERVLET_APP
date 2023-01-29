package com.epam.donetc.restaurant.сontroller.command.post;

import com.epam.donetc.restaurant.service.DishService;
import com.epam.donetc.restaurant.servlets.CartServlet;
import com.epam.donetc.restaurant.сontroller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDishCommand implements ICommand {
    private final DishService dishService = new DishService();
    private static final Logger log = LogManager.getLogger(CartServlet.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("newName");
        int prise = Integer.parseInt(req.getParameter("newPrise"));
        int weight = Integer.parseInt(req.getParameter("newWeight"));
        int category = Integer.parseInt(req.getParameter("newCategory"));
        String desc = req.getParameter("newDesc");
        log.trace(" newPrise== " + prise + " newWeight " + weight + " newCategory " + category);
        dishService.addDish(name, prise, weight, category, desc);
        return req.getContextPath() + "/controller?command=add_dish";
    }
}
