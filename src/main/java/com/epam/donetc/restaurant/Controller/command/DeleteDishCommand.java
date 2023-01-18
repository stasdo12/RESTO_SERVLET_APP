package com.epam.donetc.restaurant.Controller.command;

import com.epam.donetc.restaurant.Controller.ICommand;
import com.epam.donetc.restaurant.service.DishService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDishCommand implements ICommand {

    private static final Logger log = LogManager.getLogger(DeleteDishCommand.class);
    private final DishService dishService = new DishService();

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
        return req.getContextPath() + "/change-dish";

    }
}
