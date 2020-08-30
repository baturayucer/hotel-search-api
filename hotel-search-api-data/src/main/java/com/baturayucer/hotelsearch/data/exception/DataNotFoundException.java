package com.baturayucer.hotelsearch.data.exception;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1681535452172228136L;

    public DataNotFoundException(String s) {
        super(s);
    }
}