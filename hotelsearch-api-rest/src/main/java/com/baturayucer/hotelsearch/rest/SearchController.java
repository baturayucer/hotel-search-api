package com.baturayucer.hotelsearch.rest;

import com.baturayucer.hotelsearch.rest.model.SearchItemRequest;
import com.baturayucer.hotelsearch.rest.model.SearchItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.baturayucer.hotelsearch.rest.constant.WebControllerConstants.HOTEL_SEARCH_API;
import static com.baturayucer.hotelsearch.rest.constant.WebControllerConstants.SEARCH;

/**
 * @author baturayucer.
 */
@RequestMapping(value = HOTEL_SEARCH_API)
public interface SearchController {

    @GetMapping(value = SEARCH)
    ResponseEntity<SearchItemResponse> searchDeals(@RequestParam SearchItemRequest searchRequest);
}