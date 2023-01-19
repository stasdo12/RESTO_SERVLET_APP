package com.epam.donetc.restaurant.Controller.command.get;

import com.epam.donetc.restaurant.Controller.ICommand;
import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.service.DishService;
import com.epam.donetc.restaurant.servlets.CartServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManagerChangeCommand implements ICommand  {
    private int page = 1;
    private int recordsPerPage = 10;

    private DishService dishService = new DishService();



    private static final Logger log = LogManager.getLogger(CartServlet.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("page") != null)  {
            page = Integer.parseInt(
                    req.getParameter("page"));
        }
        List<Dish> dishList = dishService.newViewAllDishForChange(
                (page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = dishService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0
                / recordsPerPage);
        req.setAttribute("dishes", dishList);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);

        log.trace("dishes == " + dishList + " noOfPages " + noOfPages + " currentPage " + page);
        return "WEB-INF/jsp/changeDish";
    }
    }






