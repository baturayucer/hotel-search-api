package com.baturayucer.hotelsearch.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Dto object for HotelAdvertiser.
 * @author baturayucer.
 */
@Getter
@Setter
public class HotelAdvertiserDto {

    private String advertiserId;
    private String hotelId;
    private String cpc;
    private String price;
    private String currency;
    private Date availabilityStartDate;
    private Date availabilityEndDate;
}