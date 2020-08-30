package com.baturayucer.hotelsearch.rest.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UpdatePricesRequest implements Serializable {

    private static final long serialVersionUID = 1397122238285278430L;
    private String advertiserId;
    private String hotelId;
    private String cpc;
    private String price;
    private String currency;
    private Date availabilityStartDate;
    private Date availabilityEndDate;
}