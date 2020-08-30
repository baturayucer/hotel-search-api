package com.baturayucer.hotelsearch.data.model;

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

    private String id;
    private String cityId;
    private String clicks;
    private String impressions;
    private String name;
    private String rating;
    private String stars;
}