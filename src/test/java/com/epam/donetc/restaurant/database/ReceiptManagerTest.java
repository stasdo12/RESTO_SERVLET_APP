package com.epam.donetc.restaurant.database;

import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.database.entity.Status;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.ReceiptService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class ReceiptManagerTest  {

    private ReceiptService receiptService = new ReceiptService();

    private Connection c = mock(Connection.class);
    private PreparedStatement ps = mock(PreparedStatement.class);
    private ResultSet rs = mock(ResultSet.class);
    private ConnectionManager dbm = mock(ConnectionManager.class);
    private Statement stmt = mock(Statement.class);

    private void setUp() throws SQLException {
        when(dbm.get()).thenReturn(c);
        when(c.prepareStatement(any(String.class))).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(c.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(any(String.class))).thenReturn(rs);
    }



    @Test
    void changeStatus() {
    }

    @Test
    void getReceiptsByUserId() {
    }

    @Test
    void countMaxPage() {
    }

    @Test
    void getReceiptsOnPage() {
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