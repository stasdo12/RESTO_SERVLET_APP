package com.epam.donetc.restaurant.database;

import com.epam.donetc.restaurant.database.entity.Dish;
import com.epam.donetc.restaurant.database.entity.Receipt;
import com.epam.donetc.restaurant.database.entity.Status;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.service.DishService;
import com.epam.donetc.restaurant.service.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptDAO {
    private int noOfRecords;

    /**
     * Extracts data about all receipts that exist in a database
     *
     * @return list of receipts from a database
     * @throws DBException if any SQLException was caught
     */
    public  List<Receipt> getAllReceipt() throws DBException  {
        List<Receipt> receipts = new ArrayList<>();
        try(Connection connection = ConnectionManager.get()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(DBManager.GET_ALL_RECEIPT);
            while (rs.next()){
                Receipt receipt = createReceipt(rs);
                receipts.add(receipt);
            }
        } catch (SQLException e) {
            throw new DBException("Cannot get all receipt", e);
        }
        return receipts;
      }


      public List<Receipt> getAllReceiptPagination(int offset, int noOfRecords){
          UserService userService = new UserService();
        List<Receipt> list = new ArrayList<>();
        Receipt receipt = null;
        try(Connection connection = ConnectionManager.get();
        PreparedStatement ps = connection.prepareStatement(DBManager.PAGINATION_FOR_MANAGER);
        PreparedStatement psCount = connection.prepareStatement(DBManager.COUNT_FOR_MANAGER);) {
            ps.setInt(1, offset);
            ps.setInt(2, noOfRecords);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                receipt = new Receipt();
                receipt.setId(rs.getInt(1));
                receipt.setUser(userService.getUserById(rs.getInt(2)));
                receipt.setStatus(Status.getStatusById(3));
                receipt.setDishes(getDishesByReceiptId(receipt.getId()));
                receipt.setAddress(receipt.getAddress());
                receipt.countTotal();
                list.add(receipt);
            }
            rs.close();
            rs = psCount.executeQuery();
            if (rs.next()){
                this.noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        return list;


      }

    /**
     * Creates an object of Receipt class by a given result set.
     *
     * @param rs Result set from a database
     * @return new receipt object
     * @throws DBException if any SQLException was caught
     */
    private static Receipt createReceipt(ResultSet rs) throws DBException {
         UserService userService = new UserService();
        Receipt receipt;
        try {
            receipt = new Receipt(rs.getInt(1),
                    userService.getUserById(rs.getInt(2)),
                    Status.getStatusById(rs.getInt(3)));
            receipt.setDishes(getDishesByReceiptId(receipt.getId()));
            receipt.countTotal();
            receipt.setAddress(receipt.getAddress());

            System.out.println(receipt);
        } catch (SQLException ex) {
            throw new DBException("Cannot create receipt", ex);
        }
        return receipt;
    }

    /**
     * Extracts data about dishes and their amount in a receipt from a database.
     *
     * @param receiptId id of a receipt
     * @return map of dishes and their amount
     * @throws DBException if any SQLException was caught
     */
    private static Map<Dish, Integer> getDishesByReceiptId(int receiptId) throws DBException {
        DishService dishService = new DishService();
        Map<Dish, Integer> dishes = new HashMap<>();
        try (Connection con = ConnectionManager.get();
             PreparedStatement ps = con.prepareStatement(DBManager.GET_DISHES_BY_RECEIPT_ID)) {
            ps.setInt(1, receiptId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    dishes.put(dishService.getDishByID(rs.getInt(2)), rs.getInt(3));
                }
            } catch (SQLException ex) {
                throw new DBException("Cannot put dishes into map", ex);
            }
        } catch (SQLException ex) {
            throw new DBException("Cannot find dishes by receipt id", ex);
        }
        return dishes;
    }

    /**
     * Updates status of a given receipt in a database.
     *
     * @param receiptId id of a receipt
     * @param status    new status
     * @throws DBException if any SQLException was caught
     */
    public  void changeStatus(int receiptId, Status status)throws DBException{
        try(Connection connection = ConnectionManager.get();
            PreparedStatement ps = connection.prepareStatement(DBManager.CHANGE_RECEIPT_STATUS)) {
            ps.setInt(1, status.getId());
            ps.setInt(2, receiptId);
            if (ps.executeUpdate() == 0){
                throw new SQLException("Change status failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Add address to order by user id
     *
     * @param id user's id
     * @param address add address to database
     * @throws DBException if any SQLException was caught
     */
    public void addAddress (int id, String address) {
        try(Connection connection = ConnectionManager.get();
        PreparedStatement ps = connection.prepareStatement(DBManager.ADDRESS)) {
            ps.setString(1, address);
            ps.setInt(2, id);
            if (ps.executeUpdate()==0){
                throw new SQLException("Added address error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Get address to order by user id
     *
     * @param id user's id
     * @return String address
     * @throws DBException if any SQLException was caught
     */
    public String getAddress (int id){
        String address = null;
        try(Connection connection = ConnectionManager.get();
        PreparedStatement ps = connection.prepareStatement(DBManager.GET_ADDRESS)) {
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    address = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }

    /**
     * Creates a list of receipts from a data extracted from a database by user's id.
     *
     * @param userId user's id
     * @return list of user's receipts
     * @throws DBException if any SQLException was caught
     */
    public  List<Receipt> getReceiptByUserId(int userId) throws DBException{
        List<Receipt> receipts = new ArrayList<>();
        try(Connection connection = ConnectionManager.get();
        PreparedStatement ps = connection.prepareStatement(DBManager.GET_RECEIPT_BY_USER_ID)) {
            ps.setInt(1, userId);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Receipt receipt = createReceipt(rs);
                    receipts.add(receipt);
                }
            }
        } catch (SQLException e) {
            throw new DBException("Cannot find receipts by User id", e);
        }
        return receipts;
    }

    public  int countMaxPage(int amount){
        if (amount % 10 == 0){
            return amount/10;
        }else {
            return amount / 10 + 1;
        }
    }

    public  List<Receipt> getReceiptOnPage(List<Receipt> receipts, int currantPage){
        int begin = (currantPage - 1) * 10;
        if (receipts.size() > 0 && receipts.size() < begin +10 ){
            receipts = receipts.subList(begin, receipts.size());
        }else {
            receipts = receipts.subList(begin, begin + 10);
        }
        return receipts;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }
}