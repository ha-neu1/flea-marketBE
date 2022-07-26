package com.fleamarket.demo.Exception;

public class MainItemException extends RuntimeException{
    public MainItemException() {
        super();
    }

    public MainItemException(String message) {
        super(message);
    }

    public MainItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public MainItemException(Throwable cause) {
        super(cause);
    }

    protected MainItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
