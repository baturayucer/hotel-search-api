package com.baturayucer.hotelsearch.rest.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UpdatePricesResponse implements Serializable {

    private static final long serialVersionUID = -6717599649690393894L;
    private String advertiserId;
    private String hotelId;
    private String cpc;
    private String price;
    private String currency;
    private String availabilityStartDate;
    private String availabilityEndDate;
}