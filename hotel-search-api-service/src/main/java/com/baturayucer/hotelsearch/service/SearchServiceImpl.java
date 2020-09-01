package com.baturayucer.hotelsearch.service;

import com.baturayucer.hotelsearch.service.mapper.SearchServiceMapper;
import com.baturayucer.hotelsearch.service.model.*;
import com.baturayucer.hotelsearch.service.util.SearchUtils;
import com.baturayucer.hotelsearch.service.util.UpdateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Hotel Search Api Service Implementation.
 * @author baturayucer.
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger logger = LogManager.getLogger(SearchServiceImpl.class);
    private static final SearchServiceMapper searchServiceMapper = SearchServiceMapper.INSTANCE;

    private DataConfig dataConfig;

    Comparator<Offer> compareByPriceAndCpc =
            Comparator.comparing(Offer::getPrice).thenComparing(Offer::getCpc);

    Comparator<SearchOutputDto> compareByRating =
            Comparator.comparing(SearchOutputDto::getRating);

    @Autowired
    public SearchServiceImpl(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }

    @Override
    public List<SearchOutputDto> searchDeals(SearchItemDto searchItemDto) {

        logger.info("Searching deals...");
        return findDealsByHotel(dataConfig, searchItemDto);
    }

    private synchronized List<SearchOutputDto> findDealsByHotel(
            DataConfig dataConfig, SearchItemDto searchParameters) {

        List<SearchOutputDto> searchOutputs = new ArrayList<>();

        List<HotelDto> filteredHotels = SearchUtils
                .filterHotelsByCity(dataConfig.getHotels(), dataConfig.getCities());

        filteredHotels.parallelStream().forEach(hotelDto -> {

            List<Offer> availableOffers = SearchUtils.findAvailableHotelOffers(
                    dataConfig.getHotelAdvertisers(), dataConfig.getAdvertisers(), hotelDto, searchParameters);

            //Set filtered hotels to output.
            SearchOutputDto searchOutputDto =
                    searchServiceMapper.toSearchOutputDto(hotelDto);
            availableOffers.sort(compareByPriceAndCpc);
            //Set related offers to output.
            searchOutputDto.setOffers(availableOffers);
            searchOutputs.add(searchOutputDto);
        });

        searchOutputs.removeAll(Collections.singleton(null));
        searchOutputs.sort(compareByRating);
        logger.info("Searching completed");
        return searchOutputs;
    }

    @Override
    public List<UpdatePricesDto> updatePrices(List<UpdatePricesDto> updatePricesDtoList) {

        synchronized (this) {

            logger.info("Updating deals...");
            updatePricesDtoList.parallelStream().forEach(updatePricesDto ->
                    UpdateUtils.updateHotelAdvert(dataConfig.getHotelAdvertisers(), updatePricesDto));
            return updatePricesDtoList;
        }
    }
}