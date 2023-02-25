package com.epam.donetc.restaurant.controller.command.post;

import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.AppException;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.CartService;
import com.epam.donetc.restaurant.service.DishService;
import com.epam.donetc.restaurant.controller.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddToCartClientCommand implements ICommand {
    private final CartService cartService = new CartService();
    private final DishService dishService = new DishService();
   private final static Logger log = LogManager.getLogger(AddToCartClientCommand.class);

    /**
     * Called from doPost method in front-controller. Tries to add dish to cart.
     * Logs error in case if not able
     *
     * @param req to get dish id
     * @return path to redirect to execute Get method through front-controller
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int dishId = Integer.parseInt(req.getParameter("id"));
        int amount = Integer.parseInt(req.getParameter("amount"));
        try {
            if (!cartService.getCart(user.getId()).containsKey(dishService.getDishByID(dishId))) {
                cartService.addDishToCart(user.getId(), dishId, amount);
            }
        } catch (DBException ex) {
            log.error("In Client menu servlet doPost() ", ex);
            throw new AppException(ex);
        }
        return req.getContextPath() + "/controller?command=client_menu";
    }
}

