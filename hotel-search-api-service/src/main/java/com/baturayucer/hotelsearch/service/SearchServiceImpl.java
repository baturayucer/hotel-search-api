package com.baturayucer.hotelsearch.service;

import com.baturayucer.hotelsearch.data.DataReader;
import com.baturayucer.hotelsearch.data.exception.DataNotFoundException;
import com.baturayucer.hotelsearch.service.mapper.SearchServiceMapper;
import com.baturayucer.hotelsearch.service.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

import static com.baturayucer.hotelsearch.data.constant.DataReaderConstants.*;

@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger logger = LogManager.getLogger(SearchServiceImpl.class);
    private static final SearchServiceMapper searchServiceMapper = SearchServiceMapper.INSTANCE;

    private DataReader dataReader;

    private List<AdvertiserDto> advertiserDtos;
    private List<CityDto> cityDtos;
    private List<HotelAdvertiserDto> hotelAdvertiserDtos;
    private List<HotelDto> hotelDtos;

    @Autowired
    public SearchServiceImpl(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void loadData() {
        try {
            advertiserDtos = searchServiceMapper
                    .toAdvertiserDtoList(dataReader.readAdvertisers(ADVERTISERS));
            cityDtos = searchServiceMapper
                    .toCityDtoList(dataReader.readCities(CITIES));
            hotelAdvertiserDtos = searchServiceMapper
                    .toHotelAdvertiserDtoList(dataReader.readHotelAdvertisers(HOTEL_ADVERTISERS));
            hotelDtos = searchServiceMapper
                    .toHotelDtoList(dataReader.readHotels(HOTELS));
            logger.info("Data fetched.");
        } catch (IOException e) {
            logger.error("Something went wrong while reading data", e);
            throw new DataNotFoundException("Could not found data");
        }
    }

    @Override
    public SearchOutputDto searchDeals(SearchItemDto searchItemDto) {
        return new SearchOutputDto();
    }
}