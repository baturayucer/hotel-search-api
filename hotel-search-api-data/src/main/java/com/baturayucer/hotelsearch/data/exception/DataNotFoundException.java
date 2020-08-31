package com.baturayucer.hotelsearch.data.exception;


/**
 * Throws Runtime Exception if data could not found under classpath.
 * @author baturayucer.
 */
public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1681535452172228136L;

    public DataNotFoundException(String s) {
        super(s);
    }
}