package com.epam.donetc.restaurant.exeption;

public class DBException extends Exception{

    public DBException(String message, Throwable cause){
        super(message, cause);
    }

    public DBException(String message){
        super(message);
    }

    }
