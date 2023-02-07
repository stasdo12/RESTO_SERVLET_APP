package com.epam.donetc.restaurant.сontroller.command.post;

import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.AppException;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.CartService;
import com.epam.donetc.restaurant.сontroller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class MakeOrderCommand implements ICommand {

    private static Logger log = LogManager.getLogger(ChangeStatusManagerCommand.class);
    private final CartService cartService = new CartService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Map<Dish, Integer> cart = (Map<Dish, Integer>) session.getAttribute("cart");
        try{
            cartService.submitOrder(user.getId(), cart);
            cartService.cleanCart(user.getId());
        }catch (DBException ex){
            log.error("In client orders servlet doPost() ", ex);
            throw new AppException(ex);
        }
        return req.getContextPath() + "/controller?command=client_orders";
    }
}
