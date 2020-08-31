package com.baturayucer.hotelsearch.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Dto object for SearchItem.
 * @author baturayucer.
 */
@Getter
@Setter
public class SearchItemDto {

    private String city;
    private Date startDate;
    private Date endDate;
}
