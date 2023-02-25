package com.epam.donetc.restaurant.controller.command.post;

import com.epam.donetc.restaurant.controller.ICommand;
import com.epam.donetc.restaurant.service.DishService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDishCommand implements ICommand  {

    private static final Logger log = LogManager.getLogger(DeleteDishCommand.class);
    private final DishService dishService = new DishService();

    /**
     * Called from doPost method in front-controller. Tries to delete dish from database.
     * Logs error in case if not able
     *
     * @return path to redirect to execute Get method through front-controller
     */

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
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
        return req.getContextPath() + "/controller?command=change_dish";

    }
}
