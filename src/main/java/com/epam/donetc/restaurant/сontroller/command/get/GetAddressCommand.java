package com.epam.donetc.restaurant.сontroller.command.get;

import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.service.ReceiptService;
import com.epam.donetc.restaurant.сontroller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetAddressCommand implements ICommand {
    ReceiptService receiptService = new ReceiptService();
    Receipt receipt = new Receipt();


    Logger log = LogManager.getLogger(GetAddressCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String address;
        int receiptId = receipt.getId();
        address = receiptService.getAddress(receiptId);
        req.setAttribute("address", address);
        log.trace(address);
        return "/WEB-INF/jsp/manager-orders.jsp";

//        String address;
//        Receipt receipt = new Receipt();
//        int receiptId = receipt.getId();
//        req.setAttribute("receiptId", receiptId);
//        address = receiptService.getAddress(receiptId);
//        req.setAttribute("address", address);
//        log.trace(address);

    }
}
