package com.epam.donetc.restaurant.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.AppException;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.CartService;
import com.epam.donetc.restaurant.service.DishService;
import com.epam.donetc.restaurant.service.ReceiptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/menu")
public class ClientMenuServlet extends HttpServlet  {
    private int page = 1;
    DishService dishService;
    CartService cartService;
    ReceiptService receiptService;

    public ClientMenuServlet() {
        this.dishService = new DishService();
        this.cartService = new CartService();
        this.receiptService = new ReceiptService();
    }

    private static final Logger log = LogManager.getLogger(ClientMenuServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("page") != null)  {
            page = Integer.parseInt(
                    request.getParameter("page"));
        }
        String category = request.getParameter("category");
        String sortBy = request.getParameter("sortBy");
        if(sortBy == null) sortBy = "category";
//        String curPage = request.getParameter("currentPage");
//        if(curPage == null || curPage.isEmpty()) curPage = "1";
//        int currentPage = Integer.parseInt(curPage);
        HttpSession session = request.getSession();
        try{
            List<Dish> dishes;

            if(category == null || category.isEmpty() || category.equalsIgnoreCase("All")){
                int recordsPerPage = 10;
                dishes = dishService.getDishesOnePage(
                        (page - 1) * recordsPerPage,
                        recordsPerPage);
                int noOfRecords = dishService.getNoOfRecords();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0
                        / recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("currentPage", page);
            }else{
                dishes = dishService.getDishesByCategory(category);
            }
//            int maxPage = receiptService.countMaxPage(dishes.size());

            log.debug("dishes size before sorting == " + dishes.size());
            dishes = dishService.sortBy(dishes, sortBy);
            log.debug("dishes were sorted");

            log.trace("current page == " + page);
            log.debug("dishes size before getDishOnPage == " + dishes.size());
//            dishes = dishService.getDishesOnePage(dishes, currentPage);

            request.setAttribute("category", category);
//            session.setAttribute("maxPage", maxPage);
            request.setAttribute("dishes", dishes);
            request.getRequestDispatcher("/WEB-INF/jsp/client-menu.jsp").forward(request, response);
        }catch (DBException ex){
            log.error("In Client menu servlet doGet() ", ex);
            throw new AppException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int dishId = Integer.parseInt(request.getParameter("id"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        try{
            if(!cartService.getCart(user.getId()).containsKey(dishService.getDishByID(dishId))){
                cartService.addDishToCart(user.getId(), dishId, amount);
            }
        }catch (DBException ex){
            log.error("In Client menu servlet doPost() ", ex);
            throw new AppException(ex);
        }
        response.sendRedirect(request.getContextPath() + "/menu");
    }
}
