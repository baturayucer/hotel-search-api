package com.baturayucer.hotelsearch.data.exception;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Throws Runtime Exception if data could not found under classpath.
 * @author baturayucer.
 */
public class DataNotFoundException extends RuntimeException {

    private static final Logger logger = LogManager.getLogger(DataNotFoundException.class);

    private static final long serialVersionUID = -1681535452172228136L;

    public DataNotFoundException(String s, Throwable t) {
        super(s);
        logger.error(s, t.getCause());
    }
}