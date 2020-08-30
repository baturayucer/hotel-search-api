package com.baturayucer.hotelsearch.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonPropertyOrder(
{ "id", "city_id", "clicks",
  "impressions", "name", "rating", "stars"
})
public class HotelEntity {

    @JsonProperty("id")
    private String id;
    @JsonProperty("city_id")
    private String cityId;
    @JsonProperty("clicks")
    private String clicks;
    @JsonProperty("impressions")
    private String impressions;
    @JsonProperty("name")
    private String name;
    @JsonProperty("rating")
    private String rating;
    @JsonProperty("stars")
    private String stars;
}