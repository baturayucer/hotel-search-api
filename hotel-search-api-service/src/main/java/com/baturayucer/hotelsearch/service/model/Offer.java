package com.baturayucer.hotelsearch.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto object for Offer.
 * @author baturayucer.
 */
@Getter
@Setter
@NoArgsConstructor
public class Offer {

    private String advertiserId;
    private String advertiser;
    private String cpc;
    private Double price;
    private String currency;
}
