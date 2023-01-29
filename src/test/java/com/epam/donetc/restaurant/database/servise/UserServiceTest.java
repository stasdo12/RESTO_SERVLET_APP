package com.epam.donetc.restaurant.database.servise;

import com.epam.donetc.restaurant.database.ConnectionManager;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    UserService userService = mock(UserService.class);

    @Mock
    private ResultSet rs = mock(ResultSet.class);
    @Mock
    private ConnectionManager dbm = mock(ConnectionManager.class);





    private void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getUserByLogin() throws SQLException, DBException {
        User expected = createTestUser(1);
        mockResultSet(rs, expected);
        setUp();
        when(userService.getUserByLogin(expected.getLogin())).thenReturn(expected);

    }

    @Test
    void signUp() throws SQLException {
        User expected = createTestUser(3);
        mockResultSet(rs, expected);
        setUp();
        when(userService.signUp(expected.getLogin(), expected.getLogin(), expected.getEmail())).thenReturn(expected);
    }

    @Test
    void logIn() throws SQLException, DBException {
        User expected = createTestUser(3);
        mockResultSet(rs, expected);
        setUp();
        when(userService.logIn(expected.getLogin(),expected.getPassword())).thenReturn(expected);

    }

    @Test
    void getUserById() throws SQLException, DBException {
        User expected = createTestUser(2);
        mockResultSet(rs, expected);
        setUp();
        setUp();
        when(userService.getUserById(expected.getId())).thenReturn(expected);
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