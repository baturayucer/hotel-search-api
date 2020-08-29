package com.baturayucer.hotelsearch.rest;

import com.baturayucer.hotelsearch.rest.mapper.SearchItemMapper;
import com.baturayucer.hotelsearch.rest.model.SearchItemRequest;
import com.baturayucer.hotelsearch.rest.model.SearchItemResponse;
import com.baturayucer.hotelsearch.service.SearchService;
import com.baturayucer.hotelsearch.service.model.SearchOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchControllerImpl implements SearchController {

    private SearchService searchService;
    private static final SearchItemMapper searchItemMapper = SearchItemMapper.INSTANCE;

    @Autowired
    public SearchControllerImpl(SearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public ResponseEntity<SearchItemResponse> searchDeals(@RequestParam SearchItemRequest searchRequest) {

        SearchOutputDto searchOutputDto =
                searchService.searchDeals(searchItemMapper.toItemDto(searchRequest));
        return ResponseEntity.ok(searchItemMapper.toItemResponse(searchOutputDto));
    }
}