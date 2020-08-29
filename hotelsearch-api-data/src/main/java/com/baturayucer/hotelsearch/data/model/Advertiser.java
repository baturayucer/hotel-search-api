package com.baturayucer.hotelsearch.data.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "id", "advertiser_name"})
public class Advertiser {

    private String id;
    private String advertiserName;
}