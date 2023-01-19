package com.epam.donetc.restaurant.servlets;

import com.epam.donetc.restaurant.service.ReceiptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/address")
public class AddressServlet extends HttpServlet  {


    ReceiptService receiptService = new ReceiptService();
    private static final Logger log = LogManager.getLogger(CartServlet.class);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String address = req.getParameter("address");
        log.trace("UserId = " + id + " address = " + address);
        receiptService.addAddress(id, address);
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
