package com.baturayucer.hotelsearch.data;

import com.baturayucer.hotelsearch.data.model.Advertiser;
import com.baturayucer.hotelsearch.data.model.City;
import com.baturayucer.hotelsearch.data.model.Hotel;
import com.baturayucer.hotelsearch.data.model.HotelAdvertiser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class DataReaderTest {

    @Test
    public void testReadAdvertisers() throws IOException {

        List<Advertiser> advertisers =
                DataReader.readAdvertisers("src/test/resources/advertisers.csv");
        advertisers.parallelStream().forEach(advertiser -> {
            Assert.assertNotNull(advertiser.getId());
            Assert.assertNotNull(advertiser.getAdvertiserName());
        });
    }

    @Test
    public void testReadHotelAdvertisers() throws IOException {

        List<HotelAdvertiser> hotelAdvertisers =
                DataReader.readHotelAdvertisers("src/test/resources/hotel_advertiser.csv");
        hotelAdvertisers.parallelStream().forEach(hotelAdvertiser -> {
            Assert.assertNotNull(hotelAdvertiser.getAdvertiserId());
            Assert.assertNotNull(hotelAdvertiser.getHotelId());
            Assert.assertNotNull(hotelAdvertiser.getCpc());
            Assert.assertNotNull(hotelAdvertiser.getPrice());
            Assert.assertNotNull(hotelAdvertiser.getCurrency());
            Assert.assertNotNull(hotelAdvertiser.getAvailabilityStartDate());
            Assert.assertNotNull(hotelAdvertiser.getAvailabilityEndDate());
        });
    }

    @Test
    public void testReadHotels() throws IOException {

        List<Hotel> hotels =
                DataReader.readHotels("src/test/resources/hotels.csv");
        hotels.parallelStream().forEach(hotel -> {
            Assert.assertNotNull(hotel.getId());
            Assert.assertNotNull(hotel.getCityId());
            Assert.assertNotNull(hotel.getClicks());
            Assert.assertNotNull(hotel.getImpressions());
            Assert.assertNotNull(hotel.getName());
            Assert.assertNotNull(hotel.getRating());
            Assert.assertNotNull(hotel.getStars());
        });
    }

    @Test
    public void testReadCities() throws IOException {

        List<City> cities =
                DataReader.readCities("src/test/resources/cities.csv");
        cities.parallelStream().forEach(city -> {
            Assert.assertNotNull(city.getCityName());
            Assert.assertNotNull(city.getId());
        });
    }
}