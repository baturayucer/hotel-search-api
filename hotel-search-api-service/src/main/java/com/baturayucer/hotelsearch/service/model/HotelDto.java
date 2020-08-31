package com.baturayucer.hotelsearch.service.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto object for Hotel.
 * @author baturayucer.
 */
@Getter
@Setter
public class HotelDto{

    private String id;
    private String cityId;
    private String clicks;
    private String impressions;
    private String name;
    private String rating;
    private String stars;
}