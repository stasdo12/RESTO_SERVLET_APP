package com.epam.donetc.restaurant.service;


import com.epam.donetc.restaurant.database.UserDAO;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.database.interfaceDAO.IUserDAO;
import com.epam.donetc.restaurant.exeption.DBException;


public class UserService  {

    private final IUserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public UserService(IUserDAO dao) {
        this.userDAO = dao;
    }

    /**
     * Creates a new User object with data from a database extracted by user's login.
     * @see UserDAO#getUserByLogin(String) 
     * @param login login of a user
     * @return new User object
     * @throws DBException if any SQLException was caught
     */
    public User getUserByLogin(String login) throws DBException {
       return userDAO.getUserByLogin(login);
    }


    /**
     * Extracts data about a user by their id from database.
     * @see UserDAO#getUserById(int) 
     * @param id user's id
     * @return a User object
     */
    public  User getUserById(int id){
       return userDAO.getUserById(id);
    }

    /**
     * Inserts data about new user into a database and returns a User object.
     * @see UserDAO#signUp(String, String, String) 
     * @param login user's login
     * @param password user's password
     * @return a User object
     */

    public  User signUp(String login, String password, String email){
      return userDAO.signUp(login, password, email);
    }


    /**
     * Checks if user with such login and password exists in database and if so
     * creates a new User object from a result set.
     * @see UserDAO#logIn(String, String)
     * @param login user's login
     * @param password user's password
     * @return a User object
     * @throws DBException if any SQLException was caught
     */

    public  User logIn(String login, String password) throws DBException {
        return userDAO.logIn(login, password);
    }

}
