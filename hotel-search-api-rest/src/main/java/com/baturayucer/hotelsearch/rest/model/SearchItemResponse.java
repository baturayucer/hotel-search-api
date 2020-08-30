package com.baturayucer.hotelsearch.rest.model;

import com.baturayucer.hotelsearch.service.model.Offer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchItemResponse implements Serializable {

    private static final long serialVersionUID = -5495689560827751382L;
    private String hotelId;
    private String hotelName;
    private String rating;
    private String stars;
    private List<Offer> offers;
}
