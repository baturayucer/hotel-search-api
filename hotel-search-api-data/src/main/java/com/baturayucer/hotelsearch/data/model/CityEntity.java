package com.baturayucer.hotelsearch.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for City Objects.
 * @author baturayucer.
 */
@Getter
@Setter
@JsonPropertyOrder({ "id", "cityName"})
public class CityEntity {

    @JsonProperty("id")
    private String id;
    @JsonProperty("city_name")
    private String cityName;
}