package com.epam.donetc.restaurant.database.interfaceDAO;

import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.DBException;

public interface IUserDAO {

    public User getUserByLogin(String login) throws DBException;

    public  User getUserById(int id);

    public  User signUp(String login, String password, String email);

    public  User logIn(String login, String password) throws DBException;


}
