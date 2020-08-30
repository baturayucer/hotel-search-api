package com.baturayucer.hotelsearch.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SearchItemDto {

    private String city;
    private Date startDate;
    private Date endDate;
}
