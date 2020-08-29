package com.baturayucer.hotelsearch.data.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "id", "cityName"})
public class City {

    private String id;
    private String cityName;
}