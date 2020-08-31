package com.baturayucer.hotelsearch.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for Advertiser Objects.
 * @author baturayucer.
 */
@Getter
@Setter
@JsonPropertyOrder({ "id", "advertiser_name"})
public class AdvertiserEntity {

    @JsonProperty("id")
    private String id;
    @JsonProperty("advertiser_name")
    private String advertiserName;
}