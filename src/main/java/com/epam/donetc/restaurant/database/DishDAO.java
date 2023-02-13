package com.epam.donetc.restaurant.database;

import com.epam.donetc.restaurant.database.entity.Category;
import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.database.interfaceDAO.IDishDAO;
import com.epam.donetc.restaurant.exeption.DBException;

import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DishDAO implements IDishDAO {

    /**
     * This is int fields where I will count how many entries I need to skip
     */

    private int noOfRecords;

    /**
     * Creates new dish object using data from database about a dish by its id.
     *
     * @param id dish's id
     * @return an object of dish
     * @throws DBException if any SQLException was caught
     * @author Stanislav Donetc
     */
    @Override

    public Dish getDishByID(int id) throws DBException  {
        Dish dish;
        try (Connection con = ConnectionManager.get();
             PreparedStatement ps = con.prepareStatement(DBManager.GET_DISHES_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dish = new Dish(id, rs.getString(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            Category.getCategoryById(rs.getInt(5)),
                            rs.getString(6));
                    return dish;
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            throw new DBException("Cannot get dish by id", ex);
        }
    }

    /**
     * Changes data about a dish.
     * @param newName new name for dish
     * @param newPrise new price for dish
     * @param newWeight new weight for dish
     * @param newCategory new category for dish
     * @param desc new desc for dish
     * @param id dish's id
     * @author Stanislav Donetc
     */
    @Override

    public void changeDishAllValues(String newName, int newPrise, int newWeight, int newCategory, String desc, int id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement ps = connection.prepareStatement(DBManager.CHANGE_DISH_BY_ID)) {
            ps.setString(1, newName);
            ps.setInt(2, newPrise);
            ps.setInt(3, newWeight);
            ps.setInt(4, newCategory);
            ps.setString(5, desc);
            ps.setInt(6, id);
            if (ps.executeUpdate() == 0) {
                throw new SQLException("Change Dish failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    /**
     * Removes a dish from the menu
     * @param dishId id dish's id
     * @author Stanislav Donetc
     */
    @Override
    public void deleteDish(int dishId) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement ps = connection.prepareStatement(DBManager.DELETE_DISH)) {
            ps.setInt(1, dishId);
            if (ps.executeUpdate() == 0) {
                throw new SQLException("Delete dish failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Adds a new dish to the database.
     *
     * @param name The name of the dish.
     * @param price The price of the dish.
     * @param weight The weight of the dish.
     * @param category The category of the dish.
     * @param desc The description of the dish.
     */
    @Override
    public void addDish(String name, int price, int weight, int category, String desc){
        try(Connection connection = ConnectionManager.get();
        PreparedStatement ps = connection.prepareStatement(DBManager.ADD_DISH)) {
            ps.setString(1, name);// sets the first parameter to the dish name
            ps.setInt(2, price);// sets the second parameter to the dish price
            ps.setInt(3, weight);// sets the third parameter to the dish weight
            ps.setInt(4, category);// sets the fourth parameter to the dish category
            ps.setString(5, desc);// sets the fifth parameter to the dish description
            if (ps.executeUpdate() == 0){
                throw new SQLException("Delete dish failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override

    /**
     * Creates new list of dishes using data from database.
     *
     * @return list of all dishes in menu
     * @throws DBException if any SQLException was caught
     * @author Stanislav Donetc
     */
    public List<Dish> getAllDishes() throws DBException {
        List<Dish> dishes = new ArrayList<>();
        try (Connection con = ConnectionManager.get();
             PreparedStatement ps = con.prepareStatement(DBManager.GET_ALL_DISHES)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dishes.add(getDishByID(rs.getInt(1)));
            }
        } catch (SQLException ex) {
            throw new DBException("Cannot get all dishes", ex);
        }
        return dishes;
    }

    /**
     * Filters dish data received from database by dish category.
     *
     * @param category category by which it filters dishes from menu
     * @return list of dishes of a certain category
     * @throws DBException if any SQLException was caught
     * @author Stanislav Donetc
     */
    @Override
    public List<Dish> getDishesByCategory(String category) throws DBException {
        List<Dish> allDishes = getAllDishes();
        return allDishes.stream()
                .filter((a) -> a.getCategory().getName().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    /**
     * Sorts list of dishes by a chosen sorting method.
     *
     * @param dishes list of dishes that needs to be sorted
     * @param sortBy sorting method
     * @return list of sorted dishes
     * @author Stanislav Donetc
     */
    @Override
    public List<Dish> sortBy(List<Dish> dishes, String sortBy) {
        if (sortBy.equalsIgnoreCase("price")) {
            dishes = dishes.stream()
                    .sorted(Comparator.comparingInt(Dish::getPrice))
                    .collect(Collectors.toList());

        } else if (sortBy.equalsIgnoreCase("name")) {
            dishes = dishes.stream()
                    .sorted(Comparator.comparing(Dish::getName))
                    .collect(Collectors.toList());
        } else {
            dishes = dishes.stream()
                    .sorted(Comparator.comparing(Dish::getCategory))
                    .collect(Collectors.toList());
        }
        return dishes;
    }

//    public List<Dish> getDishesOnePage(List<Dish> dishes, int currentPage) {
//        int begin = (currentPage - 1) * 10;
//        if (dishes.size() > 0 && dishes.size() < begin + 10) {
//            dishes = dishes.subList(begin, dishes.size());
//        } else {
//            dishes = dishes.subList(begin, begin + 10);
//        }
//        return dishes;
//    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    /**
     * Creates a database query for a list of dishes that will be displayed on a specific page.
     * There can be no more than 10 dishes per page.
     *
     * @param offset how many records to skip from the database
     * @param noOfRecords
    how many records on 1 page
     * @return a sublist of the given list of dishes
     * @author Stanislav Donetc
     */
    @Override
    public List<Dish> newViewAllDishForChange(int offset, int noOfRecords) {
        List<Dish> list = new ArrayList<>();
        Dish dish = null;
        try (Connection con = ConnectionManager.get();
             PreparedStatement ps = con.prepareStatement(DBManager.PAGINATION);
             PreparedStatement psCount = con.prepareStatement(DBManager.COUNT);) {
            ps.setInt(1, offset);
            ps.setInt(2, noOfRecords);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dish = new Dish();
                dish.setId(rs.getInt(1));
                dish.setName(rs.getString(2));
                dish.setPrice(rs.getInt(3));
                dish.setWeight(rs.getInt(4));
                dish.setCategory(Category.getCategoryById(rs.getInt(5)));
                dish.setDescription(rs.getString(6));
                list.add(dish);
            }
            rs.close();
            rs = psCount.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

    }

}


