package com.baturayucer.hotelsearch.service;

import com.baturayucer.hotelsearch.service.model.SearchItemDto;
import com.baturayucer.hotelsearch.service.model.SearchOutputDto;
import com.baturayucer.hotelsearch.service.model.UpdatePricesDto;

import java.util.List;

/**
 * Hotel Search Api Service Interface.
 * @author baturayucer.
 */
public interface SearchService {

    List<SearchOutputDto> searchDeals(SearchItemDto searchItemDto);
    List<UpdatePricesDto> updatePrices(List<UpdatePricesDto> updatePricesDto);
}
