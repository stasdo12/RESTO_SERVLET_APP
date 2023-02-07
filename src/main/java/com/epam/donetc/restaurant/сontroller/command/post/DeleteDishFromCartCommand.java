package com.epam.donetc.restaurant.сontroller.command.post;

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

public class DeleteDishFromCartCommand implements ICommand {

    private final CartService cartService= new CartService();
    private static Logger log = LogManager.getLogger(DeleteDishFromCartCommand.class);

    /**
     * Called from doPost method in front-controller. Tries to delete dish from cart.
     * Logs error in case if not able
     *
     * @return path to redirect to execute Get method through front-controller
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int dishId = Integer.parseInt(req.getParameter("id"));
        int amount = Integer.parseInt(req.getParameter("amount"));
        log.trace("dish id == " + dishId + " amount = " + amount);
        try{
            if(amount > 0) {
                cartService.changeAmountOfDish(user.getId(), dishId, amount);
            } else{
                cartService.deleteDishFromCart(user.getId(), dishId);
            }
        }catch (DBException ex){
            log.error("In cart servlet doPost() ", ex);
            throw new AppException(ex);
        }
        return req.getContextPath() + "/controller?command=cart";
    }
}

