package com.epam.donetc.restaurant.database.interfaceDAO;

import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.exeption.DBException;

import java.util.List;

public interface IDishDAO {

    public Dish getDishByID(int id) throws DBException;

    public void changeDishAllValues(String newName, int newPrise, int newWeight, int newCategory, String desc, int id);

    public void deleteDish(int dishId);

    public void addDish(String name, int price, int weight, int category, String desc);

    public List<Dish> getAllDishes() throws DBException;

    public List<Dish> getDishesByCategory(String category) throws DBException;

    public List<Dish> sortBy(List<Dish> dishes, String sortBy);

    public List<Dish> newViewAllDishForChange(int offset, int noOfRecords);

    public int getNoOfRecords();



}
