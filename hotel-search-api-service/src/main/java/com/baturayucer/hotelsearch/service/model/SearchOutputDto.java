package com.baturayucer.hotelsearch.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Dto object for SearchOutput.
 * @author baturayucer.
 */
@Getter
@Setter
@NoArgsConstructor
public class SearchOutputDto {

    private String hotelId;
    private String hotelName;
    private String rating;
    private String stars;
    private List<Offer> offers;
}