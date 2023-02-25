package com.epam.donetc.restaurant.exeption;

/**
 * Wrapper for RuntimeException
 *
 * @author Stanislav Donetc
 * @version 1.0
 */
public class AppException extends RuntimeException {
    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }
}

