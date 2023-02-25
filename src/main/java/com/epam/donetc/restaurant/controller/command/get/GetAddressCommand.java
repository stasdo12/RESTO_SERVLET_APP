package com.epam.donetc.restaurant.controller.command.get;

import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.service.ReceiptService;
import com.epam.donetc.restaurant.controller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    }
}
