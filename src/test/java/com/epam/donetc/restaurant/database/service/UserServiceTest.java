package com.epam.donetc.restaurant.database.service;

import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.database.interfaceDAO.IUserDAO;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private IUserDAO dao;

    private UserService userService;

    @Mock
    private ResultSet rs = mock(ResultSet.class);




    @BeforeEach
    private void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        this.userService = new UserService(dao);
    }


    @Test
    void getUserByLogin_Should_Return_User() throws SQLException, DBException {
        User expected = createTestUser(1);
        when(dao.getUserByLogin(expected.getLogin())).thenReturn(expected);

    }
    @Test
    void getUserByLogin_Should_Return_Null() throws DBException, SQLException {;
        given(dao.getUserByLogin("testLogin")).willReturn(null);
        User userEx = userService.getUserByLogin("testLogin");
        assertThat(userEx).isNull();


    }

    @Test
    void signUp() throws SQLException {
        User expected = createTestUser(3);
        when(userService.signUp(expected.getLogin(), expected.getLogin(), expected.getEmail())).thenReturn(expected);
    }

    @Test
    void logIn() throws SQLException, DBException {
        User expected = createTestUser(3);
        when(userService.logIn(expected.getLogin(),expected.getPassword())).thenReturn(expected);

    }

    @Test
    void getUserById_Should_Return_User() throws SQLException, DBException {
        User expected = createTestUser(2);
        when(userService.getUserById(expected.getId())).thenReturn(expected);
    }

    @Test
    void getUserById_Should_Return_Null() throws SQLException, DBException {
        given(dao.getUserById(333)).willReturn(null);
        User userEx = userService.getUserByLogin("333");
        assertThat(userEx).isNull();
    }

    private static User createTestUser(int i) {
        return new User(i, "user" + i, "pass" +i, 1);
    }

    private static void mockResultSet(ResultSet rs, User u) throws SQLException {
        when(rs.getInt(1)).thenReturn(u.getId());
        when(rs.getString(2)).thenReturn(u.getLogin());
        when(rs.getString(3)).thenReturn(u.getPassword());
        when(rs.getInt(4)).thenReturn(u.getRoleId());
    }
}