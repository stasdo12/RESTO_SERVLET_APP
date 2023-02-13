package com.epam.donetc.restaurant.database.service;

import com.epam.donetc.restaurant.database.ReceiptDAO;
import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.database.entity.Status;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.ReceiptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReceiptServiceTestNew {
    private ReceiptService receiptService;
    private ReceiptDAO mockReceiptDao;

    @BeforeEach
    private void setUp(){
        mockReceiptDao = mock(ReceiptDAO.class);
        receiptService = new ReceiptService(mockReceiptDao);
    }

    @Test
    public void testGetAllReceipt() throws DBException {
        List<Receipt> expectedReceipt = Arrays.asList(new Receipt(), new Receipt());
        when(mockReceiptDao.getAllReceipt()).thenReturn(expectedReceipt);
        List<Receipt> actualReceipt = receiptService.getAllReceipt();
        assertEquals(expectedReceipt, actualReceipt);
        verify(mockReceiptDao).getAllReceipt();
    }

    @Test
    public void testGetReceiptByUserId() throws DBException {
        List<Receipt> expectedReceipt = Arrays.asList(new Receipt(), new Receipt());
        when(mockReceiptDao.getReceiptByUserId(1)).thenReturn(expectedReceipt);
        List<Receipt> actualReceipt = receiptService.getReceiptByUserId(1);
        assertEquals(expectedReceipt, actualReceipt);
        verify(mockReceiptDao).getReceiptByUserId(1);
    }

    @Test
    public void testAddAddress(){
        String address = "123 Main Str";
        int receiptId = 1;
        receiptService.addAddress(address, receiptId);
        verify(mockReceiptDao).addAddress(address, receiptId);
    }

    @Test
    public void testGetAddress(){
        int receiptId = 1;
        String expectedAddress = "123 Main Str";
        when(mockReceiptDao.getAddress(receiptId)).thenReturn(expectedAddress);
        String actualAddress = receiptService.getAddress(receiptId);
        assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void testChangeStatus() throws DBException{
        int receiptId = 1;
        Status status = Status.NEW;
        receiptService.changeStatus(receiptId, status);
        verify(mockReceiptDao).changeStatus(receiptId, status);
    }
}
