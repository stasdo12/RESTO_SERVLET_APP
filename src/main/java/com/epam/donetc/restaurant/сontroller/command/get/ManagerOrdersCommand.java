package com.epam.donetc.restaurant.сontroller.command.get;

import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.AppException;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.ReceiptService;
import com.epam.donetc.restaurant.сontroller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ManagerOrdersCommand implements ICommand {

    ReceiptService receiptService = new ReceiptService();

    Logger log = LogManager.getLogger(ManagerOrdersCommand.class);

    /**
     * Called from the doGet method in the front controller. Gets the required path and passes attributes from the session
     * request
     *
     * @param req to get the message attribute from the session and put it into the request
     * @return the managerOrders page after trying to display the page
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String status = req.getParameter("status");
        String curPage = req.getParameter("currentPage");
        if (curPage == null || curPage.isEmpty()) curPage = "1";
        int currentPage = Integer.parseInt(curPage);
        try {
            List<Receipt> receipts;
            receipts = receiptService.getAllReceipt();
            int maxPage = receiptService.countMaxPage(receipts.size());
            receipts = receiptService.getReceiptOnPage(receipts, currentPage);
            log.trace("current page == " + currentPage);
            log.trace("all receipts = " + receipts.size());
            session.setAttribute("maxPage", maxPage);
            session.setAttribute("receipts", receipts);
        } catch (DBException ex) {
            log.error("In manager orders servlet doGet() ", ex);
            throw new AppException(ex);
        }
        return "/WEB-INF/jsp/manager-orders";
    }
}
