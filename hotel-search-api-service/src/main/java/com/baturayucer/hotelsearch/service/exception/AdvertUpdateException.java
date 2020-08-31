package com.baturayucer.hotelsearch.service.exception;


/**
 * Throws runtime exception on any fail int updateService.
 * @author baturayucer.
 */
public class AdvertUpdateException extends RuntimeException {

    private static final long serialVersionUID = -1681535452172228136L;

    public AdvertUpdateException(String s) {
        super(s);
    }
}