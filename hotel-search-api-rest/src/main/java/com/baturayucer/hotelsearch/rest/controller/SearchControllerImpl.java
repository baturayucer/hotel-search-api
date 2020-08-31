package com.baturayucer.hotelsearch.rest.controller;

import com.baturayucer.hotelsearch.rest.mapper.SearchItemMapper;
import com.baturayucer.hotelsearch.rest.model.SearchItemResponse;
import com.baturayucer.hotelsearch.rest.model.UpdatePricesRequest;
import com.baturayucer.hotelsearch.rest.model.UpdatePricesResponse;
import com.baturayucer.hotelsearch.service.SearchService;
import com.baturayucer.hotelsearch.service.model.SearchOutputDto;
import com.baturayucer.hotelsearch.service.model.UpdatePricesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller Implementation for SearchController.class.
 * @author baturayucer.
 */
@RestController
public class SearchControllerImpl implements SearchController {

    private SearchService searchService;
    private static final SearchItemMapper searchItemMapper = SearchItemMapper.INSTANCE;

    @Autowired
    public SearchControllerImpl(SearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public ResponseEntity<List<SearchItemResponse>> searchDeals(
            String city, String startDate, String endDate) {

        List<SearchOutputDto> searchOutputDto = searchService
                .searchDeals(searchItemMapper
                        .toItemDto(city, startDate, endDate));
        return ResponseEntity.ok(
                searchItemMapper.toItemResponse(searchOutputDto));
    }

    @Override
    public ResponseEntity<List<UpdatePricesResponse>> updatePrices(
            List<UpdatePricesRequest> updatePricesRequest) {
        List<UpdatePricesDto> updatePricesDtos =
                searchService.updatePrices(
                        searchItemMapper.toUpdatePricesDto(updatePricesRequest));
        return ResponseEntity.ok(searchItemMapper
                .toUpdatePricesResponse(updatePricesDtos));
    }
}