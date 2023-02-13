package com.epam.donetc.restaurant.database.interfaceDAO;

import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.database.entity.Status;
import com.epam.donetc.restaurant.exeption.DBException;

import java.util.List;

public interface IReceiptDAO {

    public List<Receipt> getAllReceipt() throws DBException;

    public List<Receipt> getAllReceiptPagination(int offset, int noOfRecords);

    public  void changeStatus(int receiptId, Status status)throws DBException;

    public  List<Receipt> getReceiptByUserId(int userId) throws DBException;
    public void addAddress(String address, int receiptId);
    public String getAddress(int receiptId);
}
