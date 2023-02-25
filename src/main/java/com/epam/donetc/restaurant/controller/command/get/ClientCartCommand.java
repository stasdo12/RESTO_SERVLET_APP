package com.epam.donetc.restaurant.controller.command.get;

import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.AppException;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.CartService;
import com.epam.donetc.restaurant.servlets.ClientMenuServlet;
import com.epam.donetc.restaurant.controller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


public class ClientCartCommand implements ICommand  {
    private static final Logger log = LogManager.getLogger(ClientMenuServlet.class);
    CartService cartService = new CartService();

    /**
     * Called from the doGet method in the front controller. Gets the required path and passes attributes from the session
     * request
     *
     * @param req to get the message attribute from the session and put it into the request
     * @return the user's cart page after trying to display the page
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Map<Dish, Integer> cart;
        User user = (User) session.getAttribute("user");
        try{
            cart = cartService.getCart(user.getId());
            int total = 0;
            for (Dish d: cart.keySet()) {
                total += d.getPrice() * cart.get(d);
            }
            session.setAttribute("cart", cart);
            session.setAttribute("total", total);
        }catch (DBException ex){
            log.error("In cart servlet doGet()", ex);
            throw new AppException(ex);
        }
        return "/WEB-INF/jsp/cart";
    }
    }

