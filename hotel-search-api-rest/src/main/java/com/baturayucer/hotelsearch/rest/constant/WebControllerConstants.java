package com.baturayucer.hotelsearch.rest.constant;

/**
 * Constant class that contains controller paths.
 * @author baturayucer.
 */
public class WebControllerConstants {

    public static final String HOTEL_SEARCH_API = "/hotel-search-api";
    public static final String SEARCH = "/search";
    public static final String PRICE = "/price";

    private WebControllerConstants() {
        throw new IllegalStateException("This is a constant class.");
    }
}