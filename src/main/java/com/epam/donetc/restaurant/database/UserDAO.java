package com.epam.donetc.restaurant.database;

import com.epam.donetc.restaurant.exeption.DBException;
import com.epam.donetc.restaurant.database.entity.User;
import com.epam.donetc.restaurant.util.PropertiesUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO   {

    private static final String SALT = "hxSalt";
    String salt = PropertiesUtil.get(SALT);

    public  User getUserByLogin(String login) throws DBException {
        try(Connection con = ConnectionManager.get();
            PreparedStatement ps = con.prepareStatement(DBManager.GET_USER_BY_LOGIN)){
            ps.setString(1, login);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return createUser(rs);
                } else{
                    return null;
                }
            }
        }catch (SQLException ex){
            throw new DBException("Cannot find user by login", ex);
        }
    }

    public  User getUserById(int id){
        try(Connection connection = ConnectionManager.get();
        PreparedStatement ps = connection.prepareStatement(DBManager.GET_USER_BY_ID)) {
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return createUser(rs);
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private  User createUser(ResultSet rs) throws SQLException{
        return new User(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getInt(4));
    }

    public  User signUp(String login, String password, String email){
        try(Connection connection = ConnectionManager.get();
            PreparedStatement ps = connection.prepareStatement(DBManager.SIGN_UP)) {
            password = DigestUtils.md5Hex(password + salt);
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, email);
            if (ps.executeUpdate() == 0){
                throw new DBException("Sign Up Failed");
            }
            return getUserByLogin(login);
        } catch (SQLException | DBException e) {
            throw new RuntimeException(e);

        }
    }

    public  User logIn(String login, String password) throws DBException {
        try(Connection connection = ConnectionManager.get();
            PreparedStatement ps = connection.prepareStatement(DBManager.LOG_IN)){
            password = DigestUtils.md5Hex(password + salt);
            ps.setString(1, login);
            ps.setString(2, password);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return createUser(rs);
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new DBException("Login Error" + e);
        }
    }


}
