package com.epam.donetc.restaurant.service;

import com.epam.donetc.restaurant.database.ConnectionManager;
import com.epam.donetc.restaurant.database.DBManager;
import com.epam.donetc.restaurant.database.DishDAO;
import com.epam.donetc.restaurant.database.entity.Category;
import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.exeption.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DishService {

    private DishDAO dishDAO;

    public DishService() {
        this.dishDAO = new DishDAO();
    }

    public Dish getDishByID(int id) throws DBException {
        return dishDAO.getDishByID(id);
    }


    public boolean addDish(String name, int price, int weight, int category, String desc){
       return dishDAO.addDish(name, price, weight, category, desc);
    }

    public boolean changeDishAllValues(String newName, int newPrise, int newWeight, int newCategory, String desc, int id){
     return dishDAO.changeDishAllValues(newName, newPrise, newWeight, newCategory, desc, id);
    }

    public List<Dish> getAllDishes() throws DBException {
       return dishDAO.getAllDishes();
    }

    public  List<Dish> getDishesByCategory(String category) throws DBException {
        return dishDAO.getDishesByCategory(category);
    }

    public  List<Dish> sortBy(List<Dish> dishes, String sortBy){
      return dishDAO.sortBy(dishes, sortBy);
    }
    public  List<Dish> getDishesOnePage(List<Dish> dishes, int currentPage){
        return dishDAO.getDishesOnePage(dishes, currentPage);
    }


    public  List<Dish> newViewAllDishForChange(int offset, int noOfRecords) {

       return dishDAO.newViewAllDishForChange(offset, noOfRecords);

    }

    public boolean deleteDish(int dishId) {
        return dishDAO.deleteDish(dishId);
    }

    public int getNoOfRecords() {
        return dishDAO.getNoOfRecords();
    }








}
