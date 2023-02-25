package com.epam.donetc.restaurant.database.interfaceDAO;

import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.database.entity.Status;
import com.epam.donetc.restaurant.exeption.DBException;

import java.util.List;

public interface IReceiptDAO {

    List<Receipt> getAllReceipt() throws DBException;

    List<Receipt> getAllReceiptPagination(int offset, int noOfRecords);

    void changeStatus(int receiptId, Status status) throws DBException;

    List<Receipt> getReceiptByUserId(int userId) throws DBException;

    void addAddress(String address, int receiptId);

    String getAddress(int receiptId);

    List<Receipt> getAllReceiptByUserIdPagination(int userID, int offset, int noOfRecords);
}
