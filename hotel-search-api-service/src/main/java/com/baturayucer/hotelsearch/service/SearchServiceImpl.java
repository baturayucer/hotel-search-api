package com.baturayucer.hotelsearch.service;

import com.baturayucer.hotelsearch.data.DataReader;
import com.baturayucer.hotelsearch.data.exception.DataNotFoundException;
import com.baturayucer.hotelsearch.service.mapper.SearchServiceMapper;
import com.baturayucer.hotelsearch.service.model.*;
import com.baturayucer.hotelsearch.service.util.SearchUtils;
import com.baturayucer.hotelsearch.service.util.UpdateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.baturayucer.hotelsearch.data.constant.DataReaderConstants.*;

/**
 * Hotel Search Api Service Implementation..
 * @author baturayucer.
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger logger = LogManager.getLogger(SearchServiceImpl.class);
    private static final SearchServiceMapper searchServiceMapper = SearchServiceMapper.INSTANCE;

    private DataReader dataReader;

    private List<AdvertiserDto> advertisers;
    private List<CityDto> cities;
    private List<HotelAdvertiserDto> hotelAdvertisers;
    private List<HotelDto> hotels;

    @Autowired
    public SearchServiceImpl(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void loadData() {
        loadInitialData();
    }

    @Override
    public List<SearchOutputDto> searchDeals(SearchItemDto searchItemDto) {

        List<SearchOutputDto> searchOutputs = new ArrayList<>();

        Comparator<Offer> compareByPriceAndCpc =
                Comparator.comparing(Offer::getPrice).thenComparing(Offer::getCpc);

        Comparator<SearchOutputDto> compareByRating =
                Comparator.comparing(SearchOutputDto::getRating);

        List<HotelDto> filteredHotels = SearchUtils.filterHotelsByCity(hotels, cities);

        filteredHotels.parallelStream().forEach(hotelDto -> {

            List<Offer> availableOffers = SearchUtils.findAvailableHotelOffers(
                    hotelAdvertisers, advertisers, hotelDto, searchItemDto);

            //Set filtered hotels to output.
            SearchOutputDto searchOutputDto =
                    searchServiceMapper.toSearchOutputDto(hotelDto);
            availableOffers.sort(compareByPriceAndCpc);
            //Set related offers to output.
            searchOutputDto.setOffers(availableOffers);
            searchOutputs.add(searchOutputDto);
        });

        searchOutputs.sort(compareByRating);
        return searchOutputs;
    }

    @Override
    public List<UpdatePricesDto> updatePrices(List<UpdatePricesDto> updatePricesDtoList) {

        updatePricesDtoList.parallelStream().forEach(
                updatePricesDto -> UpdateUtils
                        .updateHotelAdvert(hotelAdvertisers, updatePricesDto));
        return updatePricesDtoList;
    }

    private void loadInitialData() {

        try {
            advertisers = searchServiceMapper
                    .toAdvertiserDtoList(dataReader.readAdvertisers(ADVERTISERS));
            cities = searchServiceMapper
                    .toCityDtoList(dataReader.readCities(CITIES));
            hotelAdvertisers = searchServiceMapper
                    .toHotelAdvertiserDtoList(dataReader.readHotelAdvertisers(HOTEL_ADVERTISERS));
            hotels = searchServiceMapper
                    .toHotelDtoList(dataReader.readHotels(HOTELS));
            logger.info("Fetched all data.");
        } catch (IOException e) {
            logger.error("Something went wrong while reading data", e);
            throw new DataNotFoundException("Could not found data");
        }
    }
}