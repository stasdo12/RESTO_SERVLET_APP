package com.epam.donetc.restaurant.service;


import com.epam.donetc.restaurant.database.UserDAO;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.DBException;


public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public User getUserByLogin(String login) throws DBException {
       return userDAO.getUserByLogin(login);
    }

    public  User getUserById(int id){
       return userDAO.getUserById(id);
    }

    public  User signUp(String login, String password, String email){
      return userDAO.signUp(login, password, email);
    }

    public  User logIn(String login, String password) throws DBException {
        return userDAO.logIn(login, password);
    }

}
