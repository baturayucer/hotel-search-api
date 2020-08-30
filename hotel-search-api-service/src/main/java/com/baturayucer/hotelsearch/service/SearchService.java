package com.baturayucer.hotelsearch.service;

import com.baturayucer.hotelsearch.service.model.SearchItemDto;
import com.baturayucer.hotelsearch.service.model.SearchOutputDto;

import java.util.List;

public interface SearchService {

    List<SearchOutputDto> searchDeals(SearchItemDto searchItemDto);
}
