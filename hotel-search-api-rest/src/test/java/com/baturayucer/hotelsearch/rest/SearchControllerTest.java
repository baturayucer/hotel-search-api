package com.baturayucer.hotelsearch.rest;

import com.baturayucer.hotelsearch.rest.controller.SearchControllerImpl;
import com.baturayucer.hotelsearch.rest.mapper.SearchItemMapper;
import com.baturayucer.hotelsearch.rest.model.SearchItemResponse;
import com.baturayucer.hotelsearch.rest.model.UpdatePricesRequest;
import com.baturayucer.hotelsearch.rest.model.UpdatePricesResponse;
import com.baturayucer.hotelsearch.service.SearchServiceImpl;
import com.baturayucer.hotelsearch.service.mapper.SearchServiceMapper;
import com.baturayucer.hotelsearch.service.model.SearchOutputDto;
import com.baturayucer.hotelsearch.service.model.UpdatePricesDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Matchers.any;

public class SearchControllerTest {

    @Mock
    private SearchServiceImpl searchService;
    @InjectMocks
    private SearchControllerImpl searchController;

    private SearchItemMapper mapper = SearchItemMapper.INSTANCE;

    final String hotelName = "HotelIstanbul";
    final String hotelId = "hotelId";
    final String rating = "46";
    final String stars = "4";
    final String city = "Istanbul";
    final String startDate = "20200222";
    final String endDate = "20200222";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        searchController = new SearchControllerImpl(searchService);
    }

    @Test
    public void searchDealsTest() {

        //Given
        SearchOutputDto searchOutputDto = new SearchOutputDto();
        List<SearchOutputDto> expectedOutput = new ArrayList<>();

        searchOutputDto.setHotelName(hotelName);
        searchOutputDto.setHotelId(hotelId);
        searchOutputDto.setRating(rating);
        searchOutputDto.setStars(stars);

        expectedOutput.add(searchOutputDto);

        //when
        Mockito.when(searchService.searchDeals(any())).thenReturn(expectedOutput);
        List<SearchItemResponse> actualResponse =
                searchController.searchDeals(city, startDate, endDate).getBody();

        //then
        int sizeOfList = Optional.ofNullable(actualResponse)
                .orElseThrow(RuntimeException::new).size();
        IntStream.of(sizeOfList).forEach(i -> {

            Assert.assertEquals(expectedOutput.get(i-1).getHotelId(),
                    actualResponse.get(i-1).getHotelId());
            Assert.assertEquals(expectedOutput.get(i-1).getHotelName(),
                    actualResponse.get(i-1).getHotelName());
            Assert.assertEquals(expectedOutput.get(i-1).getRating(),
                    actualResponse.get(i-1).getRating());
            Assert.assertEquals(expectedOutput.get(i-1).getStars(),
                    actualResponse.get(i-1).getStars());
        });
    }

    @Test
    public void updatePricesTest() {

        //Given
        List<UpdatePricesRequest> updatePricesRequests = new ArrayList<>();
        UpdatePricesRequest updatePricesRequest = new UpdatePricesRequest();
        updatePricesRequest.setAdvertiserId("0");
        updatePricesRequest.setAvailabilityStartDate(startDate);
        updatePricesRequest.setAvailabilityEndDate(endDate);
        updatePricesRequest.setCpc("cpc");
        updatePricesRequest.setCurrency("TRY");
        updatePricesRequest.setHotelId("1");
        updatePricesRequest.setPrice("99.9");
        updatePricesRequests.add(updatePricesRequest);

        List<UpdatePricesDto> updatePricesDtos =
                mapper.toUpdatePricesDto(updatePricesRequests);

        List<UpdatePricesResponse> expectedResponseList =
                mapper.toUpdatePricesResponse(updatePricesDtos);

        //When
        Mockito.when(searchService
                .updatePrices(any()))
                .thenReturn(updatePricesDtos);
        List<UpdatePricesResponse> actualResponse =
                searchController.updatePrices(updatePricesRequests).getBody();

        //Then
        int sizeOfList = Optional.ofNullable(actualResponse)
                .orElseThrow(RuntimeException::new).size();
        IntStream.of(sizeOfList).forEach(i ->
                assertThat(expectedResponseList.get(i-1),
                        samePropertyValuesAs(actualResponse.get(i-1))));
    }
}
