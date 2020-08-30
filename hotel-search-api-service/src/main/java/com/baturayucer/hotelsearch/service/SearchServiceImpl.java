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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.baturayucer.hotelsearch.data.constant.DataReaderConstants.*;

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

    @Override
    public List<SearchOutputDto> searchDeals(SearchItemDto searchItemDto) {

        Comparator<Offer> compareByPriceAndCpc =
                Comparator.comparing(Offer::getPrice).thenComparing(Offer::getCpc);

        Comparator<SearchOutputDto> compareByRating =
                Comparator.comparing(SearchOutputDto::getRating);

        List<SearchOutputDto> outputDtoList = new ArrayList<>();

        List<HotelDto> filteredHotelsByCity = filterHotelsByCity(hotels);

        filteredHotelsByCity.parallelStream().forEach(hotelDto -> {

            List<Offer> availableOffers =
                    findAvailableHotelOffers(hotelAdvertisers, hotelDto, searchItemDto);
            SearchOutputDto searchOutputDto =
                    searchServiceMapper.toSearchOutputDto(hotelDto);
            availableOffers.sort(compareByPriceAndCpc);
            searchOutputDto.setOffers(availableOffers);
            outputDtoList.add(searchOutputDto);
        });

        outputDtoList.sort(compareByRating);
        return outputDtoList;
    }

    @Override
    public List<UpdatePricesDto> updatePrices(List<UpdatePricesDto> updatePricesDtoList) {

        updatePricesDtoList.parallelStream().forEach(this::updateHotelAdvert);
        return updatePricesDtoList;
    }

    private List<HotelDto> filterHotelsByCity(List<HotelDto> hotels) {

        return hotels.parallelStream().filter(hotelDto -> {
            String foundCityId = findCityByHotel(cities, hotelDto).getId();
            return hotelDto.getCityId().equals(foundCityId);
        }).collect(Collectors.toList());
    }

    private CityDto findCityByHotel(List<CityDto> cities, HotelDto hotelDto) {

        return cities.parallelStream().
                filter(cityDto -> cityDto.getId().equals(hotelDto.getCityId()))
                .findAny().orElseThrow(RuntimeException::new);
    }

    private List<Offer> findAvailableHotelOffers(
            List<HotelAdvertiserDto> hotelAdvertisers, HotelDto hotelDto, SearchItemDto searchItemDto) {

        List<Offer> offerList = new ArrayList<>();

        hotelAdvertisers.parallelStream().forEach(ad ->  {
            if (ad.getHotelId().equals(hotelDto.getId()) &&
                    searchItemDto.getStartDate().after(ad.getAvailabilityStartDate()) &&
                    searchItemDto.getStartDate().before(ad.getAvailabilityEndDate())) {
                Offer offer = searchServiceMapper.toOffer(ad);
                offer.setAdvertiser(findAdvertiserNameById(advertisers, ad.getAdvertiserId()));
                offerList.add(offer);
            }
        });
        return offerList;
    }

    private String findAdvertiserNameById(List<AdvertiserDto> advertisers, String advertiserId) {

        return advertisers.parallelStream()
                .filter(advert -> advert.getId().equals(advertiserId))
                .findAny().orElseThrow(RuntimeException::new).getAdvertiserName();
    }

    private void updateHotelAdvert(UpdatePricesDto updatePricesDto) {

        final boolean[] updated = {false};
        hotelAdvertisers.parallelStream()
                .filter(ad -> ad.getAdvertiserId().equals(updatePricesDto.getAdvertiserId()) &&
                ad.getHotelId().equals(updatePricesDto.getHotelId())).
                forEach(advertiser -> {
            advertiser.setPrice(updatePricesDto.getPrice());
            updated[0] = true;
            logger.info("Advertiser updated with the Id:{}", advertiser.getAdvertiserId());
        });

        if(!updated[0]) {
            throw new RuntimeException("Could Not Update");
        }
    }
}