package com.baturayucer.hotelsearch.data;

import com.baturayucer.hotelsearch.data.model.AdvertiserEntity;
import com.baturayucer.hotelsearch.data.model.CityEntity;
import com.baturayucer.hotelsearch.data.model.HotelAdvertiserEntity;
import com.baturayucer.hotelsearch.data.model.HotelEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataReader.class})
public class DataReaderTest {

    @Autowired
    private DataReader dataReader;

    @Test
    public void testReadAdvertisers() throws IOException {

        List<AdvertiserEntity> advertiserEntities =
                dataReader.readAdvertisers("src/test/resources/advertisers.csv");
        advertiserEntities.parallelStream().forEach(advertiserEntity -> {
            Assert.assertNotNull(advertiserEntity.getId());
            Assert.assertNotNull(advertiserEntity.getAdvertiserName());
        });
    }

    @Test
    public void testReadHotelAdvertisers() throws IOException {

        List<HotelAdvertiserEntity> hotelAdvertiserEntities =
                dataReader.readHotelAdvertisers("src/test/resources/hotel_advertiser.csv");
        hotelAdvertiserEntities.parallelStream().forEach(hotelAdvertiserEntity -> {
            Assert.assertNotNull(hotelAdvertiserEntity.getAdvertiserId());
            Assert.assertNotNull(hotelAdvertiserEntity.getHotelId());
            Assert.assertNotNull(hotelAdvertiserEntity.getCpc());
            Assert.assertNotNull(hotelAdvertiserEntity.getPrice());
            Assert.assertNotNull(hotelAdvertiserEntity.getCurrency());
            Assert.assertNotNull(hotelAdvertiserEntity.getAvailabilityStartDate());
            Assert.assertNotNull(hotelAdvertiserEntity.getAvailabilityEndDate());
        });
    }

    @Test
    public void testReadHotels() throws IOException {

        List<HotelEntity> hotelEntities =
                dataReader.readHotels("src/test/resources/hotels.csv");
        hotelEntities.parallelStream().forEach(hotelEntity -> {
            Assert.assertNotNull(hotelEntity.getId());
            Assert.assertNotNull(hotelEntity.getCityId());
            Assert.assertNotNull(hotelEntity.getClicks());
            Assert.assertNotNull(hotelEntity.getImpressions());
            Assert.assertNotNull(hotelEntity.getName());
            Assert.assertNotNull(hotelEntity.getRating());
            Assert.assertNotNull(hotelEntity.getStars());
        });
    }

    @Test
    public void testReadCities() throws IOException {

        List<CityEntity> cities =
                dataReader.readCities("src/test/resources/cities.csv");
        cities.parallelStream().forEach(cityEntity -> {
            Assert.assertNotNull(cityEntity.getCityName());
            Assert.assertNotNull(cityEntity.getId());
        });
    }
}