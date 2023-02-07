package com.epam.donetc.restaurant.сontroller.command.get;

import com.epam.donetc.restaurant.сontroller.ICommand;
import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.service.DishService;
import com.epam.donetc.restaurant.servlets.CartServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManagerChangeCommand implements ICommand  {
    private int page = 1;
    private int recordsPerPage = 10;

    private DishService dishService = new DishService();



    private static final Logger log = LogManager.getLogger(CartServlet.class);

    /**
     * Called from the doGet method in the front controller. Gets the required path and passes attributes from the session
     * request
     *
     * @param req to get the message attribute from the session and put it into the request
     * @return the changeDish page after trying to display the page
     */

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("page") != null)  {
            page = Integer.parseInt(
                    req.getParameter("page"));
        }
        List<Dish> dishList = dishService.getDishesOnePage(
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






