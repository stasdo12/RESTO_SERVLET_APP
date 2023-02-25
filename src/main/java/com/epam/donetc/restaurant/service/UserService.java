package com.epam.donetc.restaurant.service;


import com.epam.donetc.restaurant.database.UserDAO;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.database.interfaceDAO.IUserDAO;
import com.epam.donetc.restaurant.exeption.DBException;

import java.util.List;


public class UserService {

    private final IUserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public UserService(IUserDAO dao) {
        this.userDAO = dao;
    }


    /**
     * Change user email and password
     *
     * @param login    users login immutable
     * @param newPass  users new pass
     * @param newEmail users new email
     * @author Stanislav Donetc
     */

    public void accountManagement(String login, String newPass, String newEmail) {
        userDAO.accountManagement(login, newPass, newEmail);
    }


    /**
     * Change user role by userId
     *
     * @param userId id of a user
     * @author Stanislav Donetc
     */

    public void changeUserRoleId(int userId) {
        userDAO.changeUserRoleId(userId);
    }


    /**
     * Get all user who are not admin.
     *
     * @return List of users
     * @author Stanislav Donetc
     */
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }


    /**
     * Creates a new User object with data from a database extracted by user's login.
     *
     * @param login login of a user
     * @return new User object
     * @throws DBException if any SQLException was caught
     * @see UserDAO#getUserByLogin(String)
     */
    public User getUserByLogin(String login) throws DBException {
        return userDAO.getUserByLogin(login);
    }


    /**
     * Extracts data about a user by their id from database.
     *
     * @param id user's id
     * @return a User object
     * @see UserDAO#getUserById(int)
     */
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    /**
     * Inserts data about new user into a database and returns a User object.
     *
     * @param login    user's login
     * @param password user's password
     * @return a User object
     * @see UserDAO#signUp(String, String, String)
     */

    public User signUp(String login, String password, String email) {
        return userDAO.signUp(login, password, email);
    }


    /**
     * Checks if user with such login and password exists in database and if so
     * creates a new User object from a result set.
     *
     * @param login    user's login
     * @param password user's password
     * @return a User object
     * @throws DBException if any SQLException was caught
     * @see UserDAO#logIn(String, String)
     */

    public User logIn(String login, String password) throws DBException {
        return userDAO.logIn(login, password);
    }

}
