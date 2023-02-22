package com.epam.donetc.restaurant.database.service;

import com.epam.donetc.restaurant.database.ReceiptDAO;
import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.database.entity.Status;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.ReceiptService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ReceiptServiceTestNew {
    public static final int USER_ID = 1;
    public static final String ADDRESS = "123 Main Str";
    @Mock
    private ReceiptDAO mockReceiptDao;

    @InjectMocks
    private ReceiptService receiptService;



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
        when(mockReceiptDao.getReceiptByUserId(USER_ID)).thenReturn(expectedReceipt);
        List<Receipt> actualReceipt = receiptService.getReceiptByUserId(USER_ID);
        assertEquals(expectedReceipt, actualReceipt);
        verify(mockReceiptDao).getReceiptByUserId(USER_ID);
    }

    @Test
    public void testAddAddress(){
        String address = ADDRESS;
        int receiptId = USER_ID;
        receiptService.addAddress(address, receiptId);
        verify(mockReceiptDao).addAddress(address, receiptId);
    }

    @Test
    public void testGetAddress(){
        int receiptId = USER_ID;
        String expectedAddress = ADDRESS;
        when(mockReceiptDao.getAddress(receiptId)).thenReturn(expectedAddress);
        String actualAddress = receiptService.getAddress(receiptId);
        assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void testChangeStatus() throws DBException{
        int receiptId = USER_ID;
        Status status = Status.NEW;
        receiptService.changeStatus(receiptId, status);
        verify(mockReceiptDao).changeStatus(receiptId, status);
    }
}
