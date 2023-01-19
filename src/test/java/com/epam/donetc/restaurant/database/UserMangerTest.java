package com.epam.donetc.restaurant.database;

import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserMangerTest  {
    UserService userService = new UserService();
    UserDAO userDAO = new UserDAO();

    private Connection c = mock(Connection.class);

    private PreparedStatement ps = mock(PreparedStatement.class);

    private ResultSet rs = mock(ResultSet.class);

    private ConnectionManager dbm = mock(ConnectionManager.class);

    private void setUp() throws SQLException {
        when(dbm.get()).thenReturn(c);
        when(c.prepareStatement(any(String.class))).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false);
    }

    @Test
    void getUserByLogin() throws SQLException, DBException {
        User expected = createTestUser(1);
        mockResultSet(rs, expected);
        setUp();
        try (MockedStatic<ConnectionManager> dbmMock = mockStatic(ConnectionManager.class)) {
            dbmMock.when(ConnectionManager::getInstance).thenReturn(dbm);
            User actual = userService.getUserByLogin(expected.getLogin());
            assertEquals (expected, actual);
        }
    }

    @Test
    void signUp() throws SQLException {
        User expected = createTestUser(3);
        mockResultSet(rs, expected);
        setUp();
        try (MockedStatic<ConnectionManager> dbmMock = mockStatic(ConnectionManager.class)) {
            dbmMock.when(ConnectionManager::getInstance).thenReturn(dbm);
            assertThrows (DBException.class, () -> userDAO.signUp(expected.getLogin(), expected.getPassword(), expected.getEmail()));
        }
    }

    @Test
    void logIn() throws SQLException, DBException {
        User expected = createTestUser(3);
        mockResultSet(rs, expected);
        setUp();
        try (MockedStatic<ConnectionManager> dbmMock = mockStatic(ConnectionManager.class)) {
            dbmMock.when(ConnectionManager::getInstance).thenReturn(dbm);
            User actual = userService.logIn(expected.getLogin(), expected.getPassword());
            assertEquals (expected, actual);
        }
    }

    @Test
    void getUserById() throws SQLException, DBException {
        User expected = createTestUser(2);
        mockResultSet(rs, expected);
        setUp();
        try (MockedStatic<ConnectionManager> dbmMock = mockStatic(ConnectionManager.class)) {
            dbmMock.when(ConnectionManager::getInstance).thenReturn(dbm);
            User actual = userService.getUserById(2);
            assertEquals (expected, actual);
        }
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