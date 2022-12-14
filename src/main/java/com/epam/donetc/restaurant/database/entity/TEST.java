package com.epam.donetc.restaurant.database.entity;

import com.epam.donetc.restaurant.database.CartDAO;
import com.epam.donetc.restaurant.exeption.DBException;

import java.util.Map;

public class TEST {
    public static void main(String[] args) throws DBException {
        Map<Dish, Integer> cart = CartDAO.getCart(1);
        System.out.println(cart);
//        CartManager.
    }
}
