package com.baturayucer.hotelsearch.rest.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UpdatePricesResponse implements Serializable {

    private static final long serialVersionUID = -6717599649690393894L;
    private String advertiserId;
    private String hotelId;
    private String cpc;
    private String price;
    private String currency;
    private Date availabilityStartDate;
    private Date availabilityEndDate;
}