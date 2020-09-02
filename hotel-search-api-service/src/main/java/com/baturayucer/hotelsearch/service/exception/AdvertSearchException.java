package com.baturayucer.hotelsearch.service.exception;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Throws runtime exception on any fail int searchService.
 * @author baturayucer.
 */
public class AdvertSearchException extends RuntimeException {

    private static final Logger logger = LogManager.getLogger(AdvertSearchException.class);

    private static final long serialVersionUID = -1681535452172228136L;

    public AdvertSearchException(String s) {
        super(s);
        logger.error(s);
    }
}