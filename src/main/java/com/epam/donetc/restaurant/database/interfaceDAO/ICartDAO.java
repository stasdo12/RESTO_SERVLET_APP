package com.epam.donetc.restaurant.database.interfaceDAO;

import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.exeption.DBException;

import java.util.Map;

public interface ICartDAO {

    public Map<Dish, Integer> getCart(int id) throws DBException;

    public void addDishToCart(int userId, int dishId, int amount) throws DBException;

    public void changeAmountOfDish(int userId, int dishId, int amount) throws DBException;


    public void deleteDishFromCart(int userId, int dishId) throws DBException;

    public void submitOrder(int userId, Map<Dish, Integer> cart) throws DBException;

    public void cleanCart(int id) throws DBException;

}
