package com.epam.donetc.restaurant.сontroller.command.post;

import com.epam.donetc.restaurant.database.entity.Status;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.AppException;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.ReceiptService;
import com.epam.donetc.restaurant.сontroller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeStatusManagerCommand implements ICommand {

    private static Logger log = LogManager.getLogger(ChangeStatusManagerCommand.class);
    private final ReceiptService receiptService = new ReceiptService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        int receiptId = Integer.parseInt(req.getParameter("id"));
        String newStatus = req.getParameter("status");
        try{
            receiptService.changeStatus(receiptId, Status.getStatusByName(newStatus));
        }catch (DBException ex){
            log.error("In manager orders servlet doPost() ", ex);
            throw new AppException(ex);
        }
        return req.getContextPath() + "controller?command=manageOrders";
    }
}