package com.epam.donetc.restaurant.database.servise;

import com.epam.donetc.restaurant.database.ConnectionManager;
import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.database.entity.Status;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.ReceiptService;
import com.epam.donetc.restaurant.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class ReceiptServiseTest {
    @Mock
    private ReceiptService receiptService = mock(ReceiptService.class);
    @Mock
    private Connection c = mock(Connection.class);
    @Mock
    private PreparedStatement ps = mock(PreparedStatement.class);
    @Mock
    private ResultSet rs = mock(ResultSet.class);
    @Mock
    private ConnectionManager dbm = mock(ConnectionManager.class);
    @Mock
    private Statement stmt = mock(Statement.class);

    @Mock
    private UserService userService = mock(UserService.class);

    private void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void getAllReceipts() throws SQLException, DBException {
        List<Receipt> expected = createTestReceipts(3);
        for (Receipt r:
                expected) {
            mockResultSet(rs, r);
        }
       setUp();
        when(receiptService.getAllReceipt()).thenReturn(expected);
    }




//    @Test
//    void changeStatus() {
//        Receipt expected = createTestReceipt(1);
//        try {
//            mockResultSet(rs, expected);
//            setUp();
//            when(receiptService.changeStatus(expected.getId(), expected.getStatus()).thenReturn(expected));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (DBException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Test
    void getReceiptsByUserId() {
        Receipt expected = createTestReceipt(1);
        try {
            mockResultSet(rs, expected);
            setUp();
            when(receiptService.getReceiptByUserId(expected.getUser().getId())).thenReturn(Collections.singletonList(expected));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
    }


    private static Receipt createTestReceipt(int i) {
        int statusId = i;
        while(statusId>6){
            statusId-=6;
        }
        return new Receipt(i,
                new User(i, "user" + i, "pass"+i, 1),
                Status.getStatusById(statusId));
    }

    private static List<Receipt> createTestReceipts(int amount) {
        List<Receipt> receipts = new ArrayList<>();
        for (int i = 1; i <= amount; ++i) {
            receipts.add(createTestReceipt(i));
        }
        return receipts;
    }

    private static void mockResultSet(ResultSet rs, Receipt r) throws SQLException {
        when(rs.getInt(1)).thenReturn(r.getId());
        when(rs.getInt(2)).thenReturn(r.getUser().getId());
        when(rs.getInt(4)).thenReturn(r.getStatus().getId());
    }
}