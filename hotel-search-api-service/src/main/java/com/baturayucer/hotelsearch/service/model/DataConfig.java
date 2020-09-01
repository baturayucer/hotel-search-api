package com.baturayucer.hotelsearch.service.model;

import com.baturayucer.hotelsearch.data.DataReader;
import com.baturayucer.hotelsearch.data.exception.DataNotFoundException;
import com.baturayucer.hotelsearch.service.SearchServiceImpl;
import com.baturayucer.hotelsearch.service.mapper.SearchServiceMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

import static com.baturayucer.hotelsearch.data.constant.DataReaderConstants.*;


@Getter
@Setter
@Configuration
public class DataConfig {

    private static final SearchServiceMapper searchServiceMapper = SearchServiceMapper.INSTANCE;
    private static final Logger logger = LogManager.getLogger(DataConfig.class);

    private List<AdvertiserDto> advertisers;
    private List<CityDto> cities;
    private List<HotelAdvertiserDto> hotelAdvertisers;
    private List<HotelDto> hotels;

    private DataReader dataReader;

    public DataConfig(DataReader dataReader) {
        this.dataReader = dataReader;
        loadInitialData();
    }

    public void loadInitialData() {

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
            throw new DataNotFoundException("Could not found data", e);
        }
    }
}
