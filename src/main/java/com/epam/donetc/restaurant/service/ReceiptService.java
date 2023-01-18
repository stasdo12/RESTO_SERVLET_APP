package com.epam.donetc.restaurant.service;

import com.epam.donetc.restaurant.database.ConnectionManager;
import com.epam.donetc.restaurant.database.DBManager;
import com.epam.donetc.restaurant.database.ReceiptDAO;
import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.database.entity.Status;
import com.epam.donetc.restaurant.exeption.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptService {

    ReceiptDAO receiptDAO;

    public ReceiptService() {
        this.receiptDAO = new ReceiptDAO();
    }

    public List<Receipt> getAllReceipt() throws DBException {
        return receiptDAO.getAllReceipt();
    }
    public  void changeStatus(int receiptId, Status status)throws DBException{
        receiptDAO.changeStatus(receiptId, status);
    }


    public  List<Receipt> getReceiptByUserId(int userId) throws DBException{
       return receiptDAO.getReceiptByUserId(userId);
    }

    public  int countMaxPage(int amount){
       return receiptDAO.countMaxPage(amount);
    }

    public  List<Receipt> getReceiptOnPage(List<Receipt> receipts, int currantPage){
        return receiptDAO.getReceiptOnPage(receipts, currantPage);
    }

    public void addAddress (int id, String address) {
       receiptDAO.addAddress(id, address);
    }

    public String getAddress (int id){
     return receiptDAO.getAddress(id);
    }




}
