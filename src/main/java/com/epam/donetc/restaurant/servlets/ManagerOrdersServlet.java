package com.epam.donetc.restaurant.servlets;

import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.database.entity.Status;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.AppException;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.ReceiptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/manageOrders")
public class ManagerOrdersServlet extends HttpServlet {

    ReceiptService receiptService;

    public ManagerOrdersServlet() {
        this.receiptService = new ReceiptService();
    }

    private static final Logger log = LogManager.getLogger(ManagerOrdersServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String status = request.getParameter("status");
        String curPage = request.getParameter("currentPage");
        if(curPage == null || curPage.isEmpty()) curPage = "1";
        int currentPage = Integer.parseInt(curPage);
        try{
            List<Receipt> receipts;
            receipts = receiptService.getAllReceipt();
            int maxPage = receiptService.countMaxPage(receipts.size());
            receipts = receiptService.getReceiptOnPage(receipts, currentPage);
            log.trace("current page == " + currentPage);
            log.trace("receipts == " + receipts);
            session.setAttribute("maxPage", maxPage);
            session.setAttribute("receipts", receipts);
            request.getRequestDispatcher("/WEB-INF/jsp/manager-orders.jsp").forward(request, response);
        } catch(DBException ex){
            log.error("In manager orders servlet doGet() ", ex);
            throw new AppException(ex);
        }
       }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int receiptId = Integer.parseInt(request.getParameter("id"));
        String newStatus = request.getParameter("status");
        try{
            receiptService.changeStatus(receiptId, Status.getStatusByName(newStatus));
        }catch (DBException ex){
            log.error("In manager orders servlet doPost() ", ex);
            throw new AppException(ex);
        }
        response.sendRedirect(request.getContextPath() + "/manageOrders");
    }
}