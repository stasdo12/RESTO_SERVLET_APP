package com.epam.donetc.restaurant.database.interfaceDAO;

import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.exeption.DBException;

import java.util.List;

public interface IDishDAO {

    Dish getDishByID(int id) throws DBException;

    void changeDishAllValues(String newName, int newPrise, int newWeight, int newCategory, String desc, int id);

    void deleteDish(int dishId);

    void addDish(String name, int price, int weight, int category, String desc);

    List<Dish> getAllDishes() throws DBException;

    List<Dish> getDishesByCategory(String category) throws DBException;

    List<Dish> sortBy(List<Dish> dishes, String sortBy);

    List<Dish> newViewAllDishForChange(int offset, int noOfRecords);

    int getNoOfRecords();


}
