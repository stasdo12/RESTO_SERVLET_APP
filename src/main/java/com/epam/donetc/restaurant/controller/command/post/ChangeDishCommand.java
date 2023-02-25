package com.epam.donetc.restaurant.controller.command.post;

import com.epam.donetc.restaurant.service.DishService;
import com.epam.donetc.restaurant.servlets.CartServlet;
import com.epam.donetc.restaurant.controller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeDishCommand implements ICommand {
    private final DishService dishService = new DishService();
    private static final Logger log = LogManager.getLogger(CartServlet.class);

    /**
     * Called from doPost method in front-controller. Tries to change dish in database.
     * Logs error in case if not able
     *
     * @return path to redirect to execute Get method through front-controller
     */

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String newName = req.getParameter("newName");
        int newPrise = Integer.parseInt(req.getParameter("newPrise"));
        int newWeight = Integer.parseInt(req.getParameter("newWeight"));
        int newCategory = Integer.parseInt(req.getParameter("newCategory"));
        String newDesc = req.getParameter("newDesc");
        int dishId = Integer.parseInt(req.getParameter("id"));
        log.trace("dish id == " + dishId + " newPrise== " + newPrise + " newWeight " + newWeight + " newCategory " + newCategory);
        try {
            if (dishId != 0) {
                dishService.changeDishAllValues(newName, newPrise, newWeight, newCategory, newDesc, dishId);
            } else {
                resp.getWriter().println("ERROR");
            }
        } catch (IOException e) {
            log.error("In changeMenu servlet doPost() ", e);
            throw new RuntimeException(e);
        }
        return req.getContextPath() + "/controller?command=change_dish";
    }

}

