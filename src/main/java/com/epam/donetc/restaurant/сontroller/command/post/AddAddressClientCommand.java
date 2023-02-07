package com.epam.donetc.restaurant.сontroller.command.post;

import com.epam.donetc.restaurant.service.ReceiptService;
import com.epam.donetc.restaurant.сontroller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAddressClientCommand implements ICommand {
    ReceiptService receiptService = new ReceiptService();

    Logger log = LogManager.getLogger(AddAddressClientCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int receiptId = Integer.parseInt(req.getParameter("receiptId"));
        String address = req.getParameter("address");
        log.trace(receiptId +  " " +  "  " +address);
        receiptService.addAddress(address, receiptId);
        return req.getContextPath() + "/controller?command=client_orders";


    }
}
