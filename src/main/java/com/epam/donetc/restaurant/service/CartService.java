package com.epam.donetc.restaurant.service;

import com.epam.donetc.restaurant.database.CartDAO;
import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.exeption.DBException;


import java.util.Map;

public class CartService  {

    CartDAO cartDAO;

    public CartService() {
        this.cartDAO = new CartDAO();
    }

    public Map<Dish, Integer> getCart(int id) throws DBException {
       return cartDAO.getCart(id);
    }

    public  void addDishToCart(int userId, int dishId, int amount) throws DBException {
        cartDAO.addDishToCart(userId, dishId, amount);
    }

    public  void changeAmountOfDish(int userId, int dishId, int amount) throws DBException {
       cartDAO.changeAmountOfDish(userId, dishId, amount);
    }

    public  void deleteDishFromCart(int userId, int dishId) throws DBException {
       cartDAO.deleteDishFromCart(userId, dishId);
    }

    public  void submitOrder(int userId, Map<Dish, Integer> cart) throws DBException {
       cartDAO.submitOrder(userId, cart);
    }

    public  void cleanCart(int id) throws DBException {
        cartDAO.cleanCart(id);
    }

    public void changeAddress(int userId, String address) throws DBException{
        cartDAO.changeAddress(userId, address);
    }


}
