package com.baturayucer.hotelsearch.service.util;

import com.baturayucer.hotelsearch.service.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class SearchUtilTest {

    private CityDto cityDto;
    private HotelDto hotelDto;
    private AdvertiserDto advertiserDto;
    private Offer offer;
    private List<CityDto> cityDtoList;
    List<HotelAdvertiserDto> hotelAdvertiserList;
    List<AdvertiserDto> advertiserList;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Before
    public void setup() throws ParseException {
        final String idIstanbul = "34";
        cityDto = new CityDto();
        cityDto.setCityName("Istanbul");
        cityDto.setId(idIstanbul);
        cityDtoList = Collections.singletonList(cityDto);
        hotelDto = new HotelDto();
        hotelDto.setCityId(idIstanbul);
        hotelDto.setId("3");

        advertiserDto = new AdvertiserDto();
        advertiserDto.setId("1");
        advertiserDto.setAdvertiserName("adName");
        advertiserList = Collections.singletonList(advertiserDto);

        HotelAdvertiserDto hotelAdvertiserDto = new HotelAdvertiserDto();
        hotelAdvertiserDto.setAdvertiserId("1");
        hotelAdvertiserDto.setAvailabilityStartDate(sdf.parse("20200212"));
        hotelAdvertiserDto.setAvailabilityEndDate(sdf.parse("20200215"));
        hotelAdvertiserDto.setHotelId("3");
        hotelAdvertiserDto.setPrice("99.99");
        hotelAdvertiserList = Collections.singletonList(hotelAdvertiserDto);

        offer = new Offer();
        offer.setAdvertiser("adName");
        offer.setPrice(99.99);
        offer.setAdvertiserId("1");
    }

    @Test
    public void testFindCityByHotel() {
        //When
        CityDto cityByHotel = SearchUtils.findCityByHotel(cityDtoList, hotelDto);

        //Then
        assertThat(cityDto, samePropertyValuesAs(cityByHotel));
    }

    @Test
    public void testFilterHotelsByCity() {

        //Given
        List<HotelDto> hotelDtoList = new ArrayList<>();
        hotelDtoList.add(hotelDto);

        //When
        List<HotelDto> actualResponse = SearchUtils.filterHotelsByCity(hotelDtoList, cityDtoList);

        //Then
        assertThat(hotelDtoList, samePropertyValuesAs(actualResponse));
    }

    @Test
    public void testFindAdvertiserNameById() {

        //When
        String adName = SearchUtils.findAdvertiserNameById(advertiserList, "1");

        //Then
        Assert.assertEquals(advertiserDto.getAdvertiserName(), adName);
    }

    @Test
    public void testFindAvailableHotelOffers() throws ParseException {

        //Given
        SearchItemDto searchItemDto = new SearchItemDto();
        searchItemDto.setCity("Istanbul");
        searchItemDto.setStartDate(sdf.parse("20200213"));
        searchItemDto.setEndDate(sdf.parse("20200214"));

        //When
        List<Offer> availableHotelOffers = SearchUtils
                .findAvailableHotelOffers(hotelAdvertiserList, advertiserList, hotelDto, searchItemDto);

        //Then
        assertThat(offer, samePropertyValuesAs(availableHotelOffers.get(0)));
    }
}
