package com.baturayucer.hotelsearch.service;

import com.baturayucer.hotelsearch.service.model.SearchItemDto;
import com.baturayucer.hotelsearch.service.model.SearchOutputDto;

public interface SearchService {

    SearchOutputDto searchDeals(SearchItemDto searchItemDto);
}
