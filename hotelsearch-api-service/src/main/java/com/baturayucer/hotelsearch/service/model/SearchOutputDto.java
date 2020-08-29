package com.baturayucer.hotelsearch.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchOutputDto {

    private String hotelId;
    private String hotelName;
    private int rating;
    private int stars;
    private List<Offer> offers;
}