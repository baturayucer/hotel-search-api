package com.baturayucer.hotelsearch.service;

import com.baturayucer.hotelsearch.service.config.DataConfig;
import com.baturayucer.hotelsearch.service.mapper.SearchServiceMapper;
import com.baturayucer.hotelsearch.service.model.*;
import com.baturayucer.hotelsearch.service.util.SearchUtils;
import com.baturayucer.hotelsearch.service.util.UpdateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SearchUtils.class, UpdateUtils.class})
public class SearchServiceTest {

    @Mock
    private DataConfig dataConfig;
    @InjectMocks
    private SearchServiceImpl searchService;

    private static final SearchServiceMapper searchServiceMapper = SearchServiceMapper.INSTANCE;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(SearchUtils.class);
        PowerMockito.mockStatic(UpdateUtils.class);
    }

    @Test
    public void searchDealsTest() throws ParseException {

        //Given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SearchItemDto searchItemDto = new SearchItemDto();
        searchItemDto.setCity("Istanbul");
        searchItemDto.setStartDate(sdf.parse("20200212"));
        searchItemDto.setStartDate(sdf.parse("20200214"));

        HotelDto hotelDto = new HotelDto();
        hotelDto.setName("Hotel Istanbul");
        hotelDto.setId("2");
        hotelDto.setImpressions("123");
        hotelDto.setRating("44");
        hotelDto.setStars("3");
        List<HotelDto> filteredHotels = Collections.singletonList(hotelDto);

        Offer offer = new Offer();
        offer.setAdvertiser("ad");
        offer.setAdvertiserId("2");
        offer.setCpc("cpc");
        offer.setCurrency("EUR");
        offer.setPrice(99.99);
        List<Offer> offerList = Collections.singletonList(offer);

        SearchOutputDto searchOutputDto = searchServiceMapper.toSearchOutputDto(hotelDto);
        searchOutputDto.setOffers(offerList);
        List<SearchOutputDto> expectedResponse = Collections.singletonList(searchOutputDto);


        //When
        PowerMockito.when(SearchUtils.filterHotelsByCity(any(), any()))
                .thenReturn(filteredHotels);
        PowerMockito.when(SearchUtils.findAvailableHotelOffers(
                any(), any(), any(), any()))
                .thenReturn(offerList);
        List<SearchOutputDto> actualResponse = searchService.searchDeals(searchItemDto);

        //Then
        IntStream.of(actualResponse.size()).forEach(i ->
                assertThat(expectedResponse.get(i-1), samePropertyValuesAs(actualResponse.get(i-1))));
    }

    @Test
    public void updatePricesTest() throws Exception {

        //Given
        UpdatePricesDto updatePricesDto = new UpdatePricesDto();
        updatePricesDto.setHotelId("2");
        updatePricesDto.setPrice("99.99");
        updatePricesDto.setCurrency("EUR");
        List<UpdatePricesDto> updatePricesDtos = Collections.singletonList(updatePricesDto);

        //When
        PowerMockito.doNothing().when(UpdateUtils.class, "updateHotelAdvert", anyList(), any());
        List<UpdatePricesDto> actualResponse = searchService.updatePrices(updatePricesDtos);

        //Then
        assertThat(updatePricesDto, samePropertyValuesAs(actualResponse.get(0)));
    }
}