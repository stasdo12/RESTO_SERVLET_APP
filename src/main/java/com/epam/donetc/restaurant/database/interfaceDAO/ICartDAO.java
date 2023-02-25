package com.epam.donetc.restaurant.database.interfaceDAO;

import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.exeption.DBException;

import java.util.Map;

public interface ICartDAO {

    Map<Dish, Integer> getCart(int id) throws DBException;

    void addDishToCart(int userId, int dishId, int amount) throws DBException;

    void changeAmountOfDish(int userId, int dishId, int amount) throws DBException;


    void deleteDishFromCart(int userId, int dishId) throws DBException;

    void submitOrder(int userId, Map<Dish, Integer> cart) throws DBException;

    void cleanCart(int id) throws DBException;

}
