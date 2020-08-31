package com.baturayucer.hotelsearch.rest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Request class for /search endpoint.
 * @author baturayucer.
 */
@Getter
@Setter
@NoArgsConstructor
public class SearchItemRequest implements Serializable {

    private static final long serialVersionUID = 5744850468366772821L;
    private String city;
    private Date startDate;
    private Date endDate;
}