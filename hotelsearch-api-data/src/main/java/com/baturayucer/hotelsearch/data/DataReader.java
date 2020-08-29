package com.baturayucer.hotelsearch.data;

import com.baturayucer.hotelsearch.data.model.Advertiser;
import com.baturayucer.hotelsearch.data.model.City;
import com.baturayucer.hotelsearch.data.model.Hotel;
import com.baturayucer.hotelsearch.data.model.HotelAdvertiser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class DataReader {

    public static List<Advertiser> readAdvertisers(String target) throws IOException {

        return (List<Advertiser>) (Object) read(target, Advertiser.class);
    }

    public static List<HotelAdvertiser> readHotelAdvertisers(String target) throws IOException {

        return (List<HotelAdvertiser>) (Object) read(target, HotelAdvertiser.class);
    }

    public static List<Hotel> readHotels(String target) throws IOException {

        return (List<Hotel>) (Object) read(target, Hotel.class);
    }

    public static List<City> readCities(String target) throws IOException {

        return (List<City>) (Object) read(target, City.class);
    }

    public static List<Object> read(String target, Class<?> type) throws IOException {

        File csvFile = new File(target);

        MappingIterator<Object> objectIter =
                new CsvMapper().readerWithTypedSchemaFor(type).readValues(csvFile);
        List<Object> items = objectIter.readAll();
        items.remove(0);
        return items;
    }

    private DataReader() {}
}