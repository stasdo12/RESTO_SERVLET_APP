package com.epam.donetc.restaurant.database;


//language=PostgreSQL

/**
 * Contains names of database command
 *
 * @author Stanislav Donetc
 * @version 1.0
 */

public class DBManager  {
    //Login and Sign Up
    public static final String LOG_IN = "SELECT * FROM users WHERE login = ? AND password = ?";
    public static final String SIGN_UP = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";

    //USER
    public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

    //DISHES
    public static final String GET_ALL_DISHES = "SELECT * FROM dish";
//
//    public static final String GET_DISHES_BY_NAME = "SELECT * FROM dish WHERE name = ?";
    public static final String GET_DISHES_BY_ID = "SELECT * FROM dish WHERE id = ?";
    public static final String GET_DISHES_BY_RECEIPT_ID = "SELECT * FROM receipt_has_dish WHERE receipt_id = ?";


    //RECEIPT
    public static final String GET_ALL_RECEIPT  = "SELECT * FROM receipt ORDER BY create_date DESC";
    public static final String GET_RECEIPT_BY_USER_ID = "SELECT * FROM receipt WHERE user_id = ? " +
            "ORDER BY  create_date DESC ";
    public static final String CHANGE_RECEIPT_STATUS = "UPDATE receipt SET status_id = ? WHERE id = ?";
    public static final String CREATE_NEW_RECEIPT_BY_USER_ID = "INSERT INTO receipt (user_id) VALUES (?)";
    public static final String PUT_DISH_INTO_RECEIPT = "INSERT INTO receipt_has_dish ( receipt_id, dish_id, amount ) VALUES (?, ?, ?)";




    //CHANGE DISHES AND ADD DISHES

    public static final String CHANGE_DISH_BY_ID = "UPDATE  dish SET name = ?, prise = ?, weight = ?, category_id = ?, description = ? WHERE id = ?";
    public static final String DELETE_DISH = "DELETE FROM dish WHERE id = ?";

    public static final String ADD_DISH = "INSERT INTO dish (name, prise, weight, category_id, description) VALUES (?, ?, ?, ?, ?);";

    //CART
    public static final String GET_CART_BY_USER_ID = "SELECT * FROM cart WHERE user_id = ?";
    public static final String PUT_DISH_INTO_CART = "INSERT INTO cart(user_id, dish_id, amount) VALUES (?,?,?)";
    public static final String UPDATE_DISH_AMOUNT_IN_CART = "UPDATE cart SET amount = ? WHERE user_id = ? AND dish_id = ?";



    public static final String DELETE_DISH_FROM_CART = "DELETE FROM cart WHERE user_id = ? AND dish_id = ?";
    public static final String CLEAR_CART = "DELETE from cart WHERE user_id =  ?";



    //PAGINATION POSTGRESQL FOR DISH
    public static final String PAGINATION_FOR_MANAGER = "SELECT * FROM receipt  ORDER BY create_date desc OFFSET ? LIMIT ? ";
    public static final String COUNT_FOR_MANAGER = "SELECT COUNT(*) FROM receipt";

    public static final String PAGINATION = "SELECT * FROM dish  ORDER BY id OFFSET ? LIMIT ? ;";
    public static final String COUNT = "SELECT COUNT(*) FROM dish";

    //add address to receipt has dish

    public static final String ADDRESS = "UPDATE receipt_has_dish SET address = ? WHERE receipt_id = ?";

    public static final String GET_ADDRESS = "SELECT address FROM receipt_has_dish WHERE receipt_id = ? LIMIT 1";

    }
