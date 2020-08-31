package com.baturayucer.hotelsearch.service.exception;


/**
 * Throws runtime exception on any fail int searchService.
 * @author baturayucer.
 */
public class AdvertSearchException extends RuntimeException {

    private static final long serialVersionUID = -1681535452172228136L;

    public AdvertSearchException(String s) {
        super(s);
    }
}