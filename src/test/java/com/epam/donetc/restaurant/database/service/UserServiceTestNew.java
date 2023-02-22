package com.epam.donetc.restaurant.database.service;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.database.interfaceDAO.IUserDAO;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTestNew {


    public static final String PASSWORD = "password";
    public static final String TEST_LOGIN = "testLogin";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String TEST_EMAIL = "testEmail";
    @Mock
    private IUserDAO userDAO;
    @InjectMocks
    private UserService userService;



    @Test
    public void testGetUserByLogin() throws DBException {
        User expectedUser = new User(1, TEST_LOGIN, PASSWORD, 1);
        when(userDAO.getUserByLogin(TEST_LOGIN)).thenReturn(expectedUser);

        User actualUser = userService.getUserByLogin(TEST_LOGIN);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testGetUserById() {
        User expectedUser = new User(1, TEST_LOGIN, PASSWORD, 1);
        when(userDAO.getUserById(1)).thenReturn(expectedUser);

        User actualUser = userService.getUserById(1);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testSignUp() {
        User expectedUser = new User(1, TEST_LOGIN, TEST_PASSWORD, 1);
        when(userDAO.signUp(TEST_LOGIN, TEST_PASSWORD, TEST_EMAIL)).thenReturn(expectedUser);

        User actualUser = userService.signUp(TEST_LOGIN, TEST_PASSWORD, TEST_EMAIL);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testLogIn() throws DBException {
        User expectedUser = new User(1, TEST_LOGIN, TEST_PASSWORD, 1);
        when(userDAO.logIn(TEST_LOGIN, TEST_PASSWORD)).thenReturn(expectedUser);

        User actualUser = userService.logIn(TEST_LOGIN, TEST_PASSWORD);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testGetAllUser(){
        List<User> expectedUsers = Arrays.asList(new User(), new User());
        when(userDAO.getAllUser()).thenReturn(expectedUsers);
        List<User> actualUser = userService.getAllUser();
        assertEquals(expectedUsers, actualUser);
        verify(userDAO).getAllUser();
    }

    @Test
    public void testChangeUserRoleId(){
        userService.changeUserRoleId(1);
        verify(userDAO).changeUserRoleId(1);
    }

}
