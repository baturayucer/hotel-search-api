package com.baturayucer.hotelsearch.service.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto object for UpdatePrices.
 * @author baturayucer.
 */
@Getter
@Setter
public class UpdatePricesDto {

    private String advertiserId;
    private String hotelId;
    private String cpc;
    private String price;
    private String currency;
    private String availabilityStartDate;
    private String availabilityEndDate;
}