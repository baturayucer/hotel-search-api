package com.baturayucer.hotelsearch.data.constant;

public class DataReaderConstants {

    private static final String RESOURCES = "src/main/resources/";
    public static final String ADVERTISERS = RESOURCES + "advertisers.csv";
    public static final String CITIES = RESOURCES + "cities.csv";
    public static final String HOTEL_ADVERTISERS = RESOURCES + "hotel_advertiser.csv";
    public static final String HOTELS = RESOURCES + "hotels.csv";

    private DataReaderConstants() throws IllegalAccessException {
        throw new IllegalAccessException("This is a constant class.");
    }
}