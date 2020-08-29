package com.baturayucer.hotelsearch.data.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder(
{ "advertiser_id", "hotel_id", "cpc", "price",
  "currency", "availability_start_date", "availability_end_date"})
public class HotelAdvertiser {

    private String advertiserId;
    private String hotelId;
    private String cpc;
    private String price;
    private String currency;
    private String availabilityStartDate;
    private String availabilityEndDate;
}