package com.epam.donetc.restaurant.database;

import com.epam.donetc.restaurant.util.PropertiesUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionManager {


    private static ConnectionManager instance;

    private static final String PASSWORD_KEY = "db.password";
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";

    private static final BasicDataSource ds = new BasicDataSource();

    static {
        loadDriver();
        ds.setUrl(PropertiesUtil.get(URL_KEY));
        ds.setUsername(PropertiesUtil.get(USERNAME_KEY));
        ds.setPassword(PropertiesUtil.get(PASSWORD_KEY));
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection get() throws SQLException {
        return ds.getConnection();
    }

    private ConnectionManager() {

    }

    public static void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void close(AutoCloseable closeable) {
        try {
            closeable.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }
}