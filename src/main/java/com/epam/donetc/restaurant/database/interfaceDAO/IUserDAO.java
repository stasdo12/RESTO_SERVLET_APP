package com.epam.donetc.restaurant.database.interfaceDAO;

import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.DBException;

import java.util.List;

public interface IUserDAO {
    void changeUserRoleId(int userId);

    User getUserByLogin(String login) throws DBException;

    User getUserById(int id);

    User signUp(String login, String password, String email);

    User logIn(String login, String password) throws DBException;

    List<User> getAllUser();


}
