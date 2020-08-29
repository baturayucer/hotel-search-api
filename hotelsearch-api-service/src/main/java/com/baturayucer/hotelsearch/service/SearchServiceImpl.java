package com.baturayucer.hotelsearch.service;

import com.baturayucer.hotelsearch.service.model.SearchItemDto;
import com.baturayucer.hotelsearch.service.model.SearchOutputDto;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    @Override
    public SearchOutputDto searchDeals(SearchItemDto searchItemDto) {
        return new SearchOutputDto();
    }
}