package com.baturayucer.hotelsearch.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;


/**
 * Entity class for HotelAdvertiser Objects.
 * @author baturayucer.
 */
@Getter
@Setter
@JsonPropertyOrder(
{ "advertiser_id", "hotel_id", "cpc", "price",
  "currency", "availability_start_date", "availability_end_date"})
public class HotelAdvertiserEntity {

    @JsonProperty("advertiser_id")
    private String advertiserId;
    @JsonProperty("hotel_id")
    private String hotelId;
    @JsonProperty("cpc")
    private String cpc;
    @JsonProperty("price")
    private String price;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("availability_start_date")
    private String availabilityStartDate;
    @JsonProperty("availability_end_date")
    private String availabilityEndDate;
}