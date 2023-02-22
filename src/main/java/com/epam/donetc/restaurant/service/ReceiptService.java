package com.epam.donetc.restaurant.service;
import com.epam.donetc.restaurant.database.ReceiptDAO;
import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.database.entity.Status;
import com.epam.donetc.restaurant.exeption.DBException;
import java.util.List;

public class ReceiptService  {

    ReceiptDAO receiptDAO;

    public ReceiptService() {
        this.receiptDAO = new ReceiptDAO();
    }

    public ReceiptService(ReceiptDAO dao) {
        this.receiptDAO = dao;
    }

    /**
     * Extracts data about all receipts that exist in a database
     * @see ReceiptDAO#getAllReceipt() 
     * @return list of receipts from a database
     * @throws DBException if any SQLException was caught
     */

    public List<Receipt> getAllReceipt() throws DBException {
        return receiptDAO.getAllReceipt();
    }
    /**
     * Updates status of a given receipt in a database.
     * @see ReceiptDAO#changeStatus(int, Status) 
     * @param receiptId id of a receipt
     * @param status    new status
     * @throws DBException if any SQLException was caught
     */
    public  void changeStatus(int receiptId, Status status)throws DBException{
        receiptDAO.changeStatus(receiptId, status);
    }


    /**
     * Creates a list of receipts from a data extracted from a database by user's id.
     * @see ReceiptDAO#getReceiptByUserId(int) 
     * @param userId user's id
     * @return list of user's receipts
     * @throws DBException if any SQLException was caught
     */

    public  List<Receipt> getReceiptByUserId(int userId) throws DBException{
       return receiptDAO.getReceiptByUserId(userId);
    }

    public List<Receipt> getAllReceiptPagination(int offset, int noOfRecords){
       return receiptDAO.getAllReceiptPagination(offset, noOfRecords);
    }
    public int getNoOfRecords() {
        return receiptDAO.getNoOfRecords();
    }

    public  int countMaxPage(int amount){
       return receiptDAO.countMaxPage(amount);
    }

    public  List<Receipt> getReceiptOnPage(List<Receipt> receipts, int currantPage){
        return receiptDAO.getReceiptOnPage(receipts, currantPage);
    }

    /**
     *Add address to receipt
     * @param address Address String
     * @param receiptId receipt id where we add this address
     * @author Stanislav Donetc
     */

    public void addAddress(String address, int receiptId){
        receiptDAO.addAddress(address, receiptId);
    }

    /**
     *Get address from receipt
     * @param receiptId receipt id where we get address
     * @return String Address
     * @author Stanislav Donetc
     */

    public String getAddress(int receiptId){
       return receiptDAO.getAddress(receiptId);
    }

    public List<Receipt> getAllReceiptByUserIdPagination(int userID, int offset, int noOfRecords ){
        return receiptDAO.getAllReceiptByUserIdPagination(userID, offset, noOfRecords);
    }

}
