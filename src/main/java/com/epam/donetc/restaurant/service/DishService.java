package com.epam.donetc.restaurant.service;

import com.epam.donetc.restaurant.database.DishDAO;

import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.database.interfaceDAO.IDishDAO;
import com.epam.donetc.restaurant.exeption.DBException;
import java.util.List;


public class DishService  {

    private final IDishDAO dishDAO;

    public DishService() {
        this.dishDAO = new DishDAO();
    }

    public DishService(IDishDAO dao) {
        this.dishDAO = dao;
    }

    /**
     * Creates new dish object using data from database about a dish by its id.
     ** @see DishDAO#getDishByID(int)
     * @param id dish's id
     * @return an object of dish
     * @throws DBException if any SQLException was caught
     */

    public Dish getDishByID(int id) throws DBException {
        return dishDAO.getDishByID(id);
    }

    /**
     * Adds a dish to the menu
     * @see DishDAO#addDish(String, int, int, int, String)
     * @param name new name for dish
     * @param price new price for dish
     * @param weight new weight for dish
     * @param category new category for dish
     * @param desc new desc for dish
     */
    public void addDish(String name, int price, int weight, int category, String desc){
        dishDAO.addDish(name, price, weight, category, desc);
    }
    /**
     * Changes data about a dish.
     * @see DishDAO#changeDishAllValues(String, int, int, int, String, int)
     * @param newName new name for dish
     * @param newPrise new price for dish
     * @param newWeight new weight for dish
     * @param newCategory new category for dish
     * @param desc new desc for dish
     * @param id dish's id
     */
    public void changeDishAllValues(String newName, int newPrise, int newWeight, int newCategory, String desc, int id){
        dishDAO.changeDishAllValues(newName, newPrise, newWeight, newCategory, desc, id);
    }
    /**
     * Creates new list of dishes using data from database.
     * @see DishDAO#getAllDishes()
     * @return list of all dishes in menu
     * @throws DBException if any SQLException was caught
     */
    public List<Dish> getAllDishes() throws DBException {
       return dishDAO.getAllDishes();
    }

    /**
     * Filters dish data received from database by dish category.
     * @see DishDAO#getDishesByCategory(String)
     * @param category category by which it filters dishes from menu
     * @return list of dishes of a certain category
     * @throws DBException if any SQLException was caught
     */
    public  List<Dish> getDishesByCategory(String category) throws DBException {
        return dishDAO.getDishesByCategory(category);
    }

    /**
     * Sorts list of dishes by a chosen sorting method.
     * @see DishDAO#sortBy(List, String)
     * @param dishes list of dishes that needs to be sorted
     * @param sortBy sorting method
     * @return list of sorted dishes
     */
    public  List<Dish> sortBy(List<Dish> dishes, String sortBy){
      return dishDAO.sortBy(dishes, sortBy);
    }


    /**
     * Creates a database query for a list of dishes that will be displayed on a specific page.
     * There can be no more than 10 dishes per page.
     * @see DishDAO#newViewAllDishForChange(int, int) 
     * @param offset how many records to skip from the database
     * @param noOfRecords
    how many records on 1 page
     * @return a sublist of the given list of dishes
     */
    public  List<Dish> getDishesOnePage(int offset, int noOfRecords) {

       return dishDAO.newViewAllDishForChange(offset, noOfRecords);

    }

    /**
     * Removes a dish from the menu
     * @see DishDAO#deleteDish(int) 
     * @param dishId id dish's id
     */
    public void deleteDish(int dishId) {
        dishDAO.deleteDish(dishId);
    }

    public int getNoOfRecords() {
        return dishDAO.getNoOfRecords();
    }








}
