package com.epam.donetc.restaurant.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.AppException;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.CartService;
import com.epam.donetc.restaurant.service.ReceiptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/myOrders")
public class ClientOrdersServlet extends HttpServlet  {

    CartService cartService;
    ReceiptService receiptService;

    public ClientOrdersServlet() {
        this.cartService = new CartService();
        this.receiptService = new ReceiptService();
    }

    private static final Logger log = LogManager.getLogger(ClientOrdersServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String curPage = request.getParameter("currentPage");
        if(curPage == null || curPage.isEmpty()) curPage = "1";
        int currentPage = Integer.parseInt(curPage);
        try{

            List<Receipt> receipts;
            receipts = receiptService.getReceiptByUserId(user.getId());
            if(receipts.size()>0){
                int maxPage = receiptService.countMaxPage(receipts.size());
                receipts = receiptService.getReceiptOnPage(receipts, currentPage);
                log.trace("current page == " + currentPage);
                log.trace("receipts == " + receipts);
                session.setAttribute("maxPage", maxPage);
            }
            session.setAttribute("receipts", receipts);
            request.getRequestDispatcher("/WEB-INF/jsp/client-orders.jsp").forward(request, response);
        } catch(DBException ex){
            log.error("In client orders servlet doGet() ", ex);
            throw new AppException(ex);
          }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Map<Dish, Integer> cart = (Map<Dish, Integer>) session.getAttribute("cart");
        try{
            cartService.submitOrder(user.getId(), cart);
            cartService.cleanCart(user.getId());
        }catch (DBException ex){
            log.error("In client orders servlet doPost() ", ex);
            throw new AppException(ex);
        }
        response.sendRedirect(request.getContextPath() + "/myOrders");
    }
}