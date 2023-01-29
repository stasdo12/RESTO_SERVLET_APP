package com.epam.donetc.restaurant.сontroller.command.get;

import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.exeption.AppException;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.DishService;
import com.epam.donetc.restaurant.сontroller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ClientMenuCommand implements ICommand {
    private int page = 1;

    DishService dishService = new DishService();

    Logger log = LogManager.getLogger(ClientMenuCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(
                    req.getParameter("page"));
        }
        String category = req.getParameter("category");
        String sortBy = req.getParameter("sortBy");
        if (sortBy == null) sortBy = "category";
//        String curPage = request.getParameter("currentPage");
//        if(curPage == null || curPage.isEmpty()) curPage = "1";
//        int currentPage = Integer.parseInt(curPage);
        HttpSession session = req.getSession();
        try {
            List<Dish> dishes;

            if (category == null || category.isEmpty() || category.equalsIgnoreCase("All")) {
                int recordsPerPage = 10;
                dishes = dishService.newViewAllDishForChange(
                        (page - 1) * recordsPerPage,
                        recordsPerPage);
                int noOfRecords = dishService.getNoOfRecords();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0
                        / recordsPerPage);
                req.setAttribute("noOfPages", noOfPages);
                req.setAttribute("currentPage", page);
            } else {
                dishes = dishService.getDishesByCategory(category);
            }
//            int maxPage = receiptService.countMaxPage(dishes.size());

            log.debug("dishes size before sorting == " + dishes.size());
            dishes = dishService.sortBy(dishes, sortBy);
            log.debug("dishes were sorted");

            log.trace("current page == " + page);
            log.debug("dishes size before getDishOnPage == " + dishes.size());
//            dishes = dishService.getDishesOnePage(dishes, currentPage);

            req.setAttribute("category", category);
//            session.setAttribute("maxPage", maxPage);
            req.setAttribute("dishes", dishes);

        } catch (DBException ex) {
            log.error("In Client menu servlet doGet() ", ex);
            throw new AppException(ex);
        }
        return "/WEB-INF/jsp/client-menu";
    }
}
