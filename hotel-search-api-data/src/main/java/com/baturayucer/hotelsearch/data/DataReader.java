package com.baturayucer.hotelsearch.data;

import com.baturayucer.hotelsearch.data.model.AdvertiserEntity;
import com.baturayucer.hotelsearch.data.model.CityEntity;
import com.baturayucer.hotelsearch.data.model.HotelAdvertiserEntity;
import com.baturayucer.hotelsearch.data.model.HotelEntity;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class DataReader {

    private static final Logger logger = LogManager.getLogger(DataReader.class);

    public List<AdvertiserEntity> readAdvertisers(String target) throws IOException {

        logger.info("Fetching Advertisers");
        return (List<AdvertiserEntity>) (Object) read(target, AdvertiserEntity.class);
    }

    public List<HotelAdvertiserEntity> readHotelAdvertisers(String target) throws IOException {

        logger.info("Fetching Hotel Advertisers");
        return (List<HotelAdvertiserEntity>) (Object) read(target, HotelAdvertiserEntity.class);
    }

    public List<HotelEntity> readHotels(String target) throws IOException {

        logger.info("Fetching Hotels");
        return (List<HotelEntity>) (Object) read(target, HotelEntity.class);
    }

    public List<CityEntity> readCities(String target) throws IOException {

        logger.info("Fetching Cities");
        return (List<CityEntity>) (Object) read(target, CityEntity.class);
    }

    private List<Object> read(String target, Class<?> type) throws IOException {

        File csvFile = new File(target);

        MappingIterator<Object> objectIter =
                new CsvMapper().readerWithTypedSchemaFor(type).readValues(csvFile);
        List<Object> items = objectIter.readAll();
        items.remove(0);
        return items;
    }

    private DataReader() {}
}