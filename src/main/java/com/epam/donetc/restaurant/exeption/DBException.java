package com.epam.donetc.restaurant.exeption;

/**
 * Wrapper for SQLException
 *
 * @author Stanislav Donetc
 * @version 1.0
 */
public class DBException extends Exception {

    public DBException(String message, Throwable cause){
        super(message, cause);
    }

    public DBException(String message){
        super(message);
    }

    }
