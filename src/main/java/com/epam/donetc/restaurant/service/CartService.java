package com.epam.donetc.restaurant.service;

import com.epam.donetc.restaurant.database.CartDAO;
import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.database.interfaceDAO.ICartDAO;
import com.epam.donetc.restaurant.exeption.DBException;


import java.util.Map;

public class CartService  {

  private final ICartDAO cartDAO;

    public CartService() {
        this.cartDAO = new CartDAO();
    }

    public CartService(ICartDAO dao) {
        this.cartDAO = dao;
    }

    /**
     * Extracts data about cart of certain user from database
     * @see CartDAO#getCart(int) 
     * @param id user's id
     * @return a map of dishes and their amount in a cart
     * @throws DBException if any SQLException was caught
     */

    public Map<Dish, Integer> getCart(int id) throws DBException {
       return cartDAO.getCart(id);
    }
    /**
     * Inserts data about new dish in a cart of certain user into database.
     * @see CartDAO#addDishToCart(int, int, int) 
     * @param userId user's id
     * @param dishId dish's id
     * @param amount amount of dish
     * @throws DBException if any SQLException was caught
     */
    public  void addDishToCart(int userId, int dishId, int amount) throws DBException {
        cartDAO.addDishToCart(userId, dishId, amount);
    }
    /**
     * Updates amount of dish in user's cart in a database.
     * @see CartDAO#changeAmountOfDish(int, int, int) 
     * @param userId user's id
     * @param dishId dish's id
     * @param amount amount of dish that needs to be updated
     * @throws DBException if any SQLException was caught
     */
    public  void changeAmountOfDish(int userId, int dishId, int amount) throws DBException {
       cartDAO.changeAmountOfDish(userId, dishId, amount);
    }

    /**
     * Deletes dish from a cart of certain user.
     * @see CartDAO#deleteDishFromCart(int, int) 
     * @param userId user's id
     * @param dishId dish's id
     * @throws DBException if any SQLException was caught
     */
    public  void deleteDishFromCart(int userId, int dishId) throws DBException {
       cartDAO.deleteDishFromCart(userId, dishId);
    }
    /**
     * Creates new receipt in database and inserts data about dishes in it.
     * @see CartDAO#submitOrder(int, Map) 
     * @param userId user's id
     * @param cart user's cart represented by a map of dishes and their amount
     * @throws DBException if any SQLException was caught
     */
    public  void submitOrder(int userId, Map<Dish, Integer> cart) throws DBException {
       cartDAO.submitOrder(userId, cart);
    }
    /**
     * Deletes all data about user's cart and dishes in it.
     * @see CartDAO#cleanCart(int) 
     * @param id user's id
     * @throws DBException if any SQLException was caught
     */
    public  void cleanCart(int id) throws DBException {
        cartDAO.cleanCart(id);
    }

//    public void changeAddress(int userId, String address) throws DBException{
//        cartDAO.changeAddress(userId, address);
//    }


}
