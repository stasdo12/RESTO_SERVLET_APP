package com.epam.donetc.restaurant.servlets;


import com.epam.donetc.restaurant.database.entity.Dish;
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
import java.util.List;

@WebServlet("/change-dish")
public class ChangeDishManagerServlet extends HttpServlet {


    private int page = 1;

    private final DishService dishService;


    public ChangeDishManagerServlet() {
        this.dishService = new DishService();
    }

    private static final Logger log = LogManager.getLogger(CartServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("page") != null)  {
            page = Integer.parseInt(
                    req.getParameter("page"));
        }
        int recordsPerPage = 10;
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
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsp/changeDish.jsp");
        view.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newName = request.getParameter("newName");
        int newPrise = Integer.parseInt(request.getParameter("newPrise"));
        int newWeight = Integer.parseInt(request.getParameter("newWeight"));
        int newCategory = Integer.parseInt(request.getParameter("newCategory"));
        String newDesc = request.getParameter("newDesc");
        int dishId = Integer.parseInt(request.getParameter("id"));
        log.trace("dish id == " + dishId + " newPrise== " + newPrise + " newWeight " + newWeight + " newCategory " + newCategory);
        try {
            if (dishId != 0) {
                dishService.changeDishAllValues(newName, newPrise, newWeight, newCategory, newDesc, dishId);
            } else {
                response.getWriter().println("ERROR");
            }
        } catch (IOException e) {
            log.error("In changeMenu servlet doPost() ", e);
            throw new RuntimeException(e);
        }
        response.sendRedirect(request.getContextPath() + "/change-dish");
    }


}


