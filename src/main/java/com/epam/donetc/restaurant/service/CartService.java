package com.epam.donetc.restaurant.service;

import com.epam.donetc.restaurant.database.CartDAO;
import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.database.interfaceDAO.ICartDAO;
import com.epam.donetc.restaurant.exeption.DBException;


import java.util.Map;

public class CartService {

    private final ICartDAO cartDAO;

    public CartService() {
        this.cartDAO = new CartDAO();
    }

    public CartService(ICartDAO dao) {
        this.cartDAO = dao;
    }

    /**
     * Extracts data about cart of certain user from database
     *
     * @param id user's id
     * @return a map of dishes and their amount in a cart
     * @throws DBException if any SQLException was caught
     * @see CartDAO#getCart(int)
     */

    public Map<Dish, Integer> getCart(int id) throws DBException {
        return cartDAO.getCart(id);
    }

    /**
     * Inserts data about new dish in a cart of certain user into database.
     *
     * @param userId user's id
     * @param dishId dish's id
     * @param amount amount of dish
     * @throws DBException if any SQLException was caught
     * @see CartDAO#addDishToCart(int, int, int)
     */
    public void addDishToCart(int userId, int dishId, int amount) throws DBException {
        cartDAO.addDishToCart(userId, dishId, amount);
    }

    /**
     * Updates amount of dish in user's cart in a database.
     *
     * @param userId user's id
     * @param dishId dish's id
     * @param amount amount of dish that needs to be updated
     * @throws DBException if any SQLException was caught
     * @see CartDAO#changeAmountOfDish(int, int, int)
     */
    public void changeAmountOfDish(int userId, int dishId, int amount) throws DBException {
        cartDAO.changeAmountOfDish(userId, dishId, amount);
    }

    /**
     * Deletes dish from a cart of certain user.
     *
     * @param userId user's id
     * @param dishId dish's id
     * @throws DBException if any SQLException was caught
     * @see CartDAO#deleteDishFromCart(int, int)
     */
    public void deleteDishFromCart(int userId, int dishId) throws DBException {
        cartDAO.deleteDishFromCart(userId, dishId);
    }

    /**
     * Creates new receipt in database and inserts data about dishes in it.
     *
     * @param userId user's id
     * @param cart   user's cart represented by a map of dishes and their amount
     * @throws DBException if any SQLException was caught
     * @see CartDAO#submitOrder(int, Map)
     */
    public void submitOrder(int userId, Map<Dish, Integer> cart) throws DBException {
        cartDAO.submitOrder(userId, cart);
    }

    /**
     * Deletes all data about user's cart and dishes in it.
     *
     * @param id user's id
     * @throws DBException if any SQLException was caught
     * @see CartDAO#cleanCart(int)
     */
    public void cleanCart(int id) throws DBException {
        cartDAO.cleanCart(id);
    }

}
