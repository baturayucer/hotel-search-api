package com.baturayucer.hotelsearch.rest.controller;

import com.baturayucer.hotelsearch.rest.model.SearchItemResponse;
import com.baturayucer.hotelsearch.rest.model.UpdatePricesRequest;
import com.baturayucer.hotelsearch.rest.model.UpdatePricesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.baturayucer.hotelsearch.rest.constant.WebControllerConstants.*;

/**
 * Controller Interface for Hotel Search Api.
 * @author baturayucer.
 */
@RequestMapping(value = HOTEL_SEARCH_API)
public interface SearchController {

    @GetMapping(value = SEARCH)
    ResponseEntity<List<SearchItemResponse>> searchDeals(
            @RequestParam String city,
            @RequestParam String startDate,
            @RequestParam String endDate);

    @PostMapping(value = PRICE)
    ResponseEntity<List<UpdatePricesResponse>> updatePrices(
            @RequestBody List<UpdatePricesRequest> updatePricesRequest);
}