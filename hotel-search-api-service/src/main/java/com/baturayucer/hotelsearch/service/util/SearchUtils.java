package com.baturayucer.hotelsearch.service.util;

import com.baturayucer.hotelsearch.service.mapper.SearchServiceMapper;
import com.baturayucer.hotelsearch.service.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class SearchUtils {

    private static final SearchServiceMapper mapper = SearchServiceMapper.INSTANCE;

    public static List<HotelDto> filterHotelsByCity(List<HotelDto> hotels, List<CityDto> cityList) {

        return hotels.parallelStream().filter(hotelDto -> {
            String foundCityId = SearchUtils.findCityByHotel(cityList, hotelDto).getId();
            return hotelDto.getCityId().equals(foundCityId);
        }).collect(Collectors.toList());
    }

    public static CityDto findCityByHotel(List<CityDto> cities, HotelDto hotelDto) {

        return cities.parallelStream().
                filter(cityDto -> cityDto.getId().equals(hotelDto.getCityId()))
                .findAny().orElseThrow(RuntimeException::new);
    }

    public static String findAdvertiserNameById(List<AdvertiserDto> advertisers, String advertiserId) {

        return advertisers.parallelStream()
                .filter(advert -> advert.getId().equals(advertiserId))
                .findAny().orElseThrow(RuntimeException::new).getAdvertiserName();
    }

    public static List<Offer> findAvailableHotelOffers(
            List<HotelAdvertiserDto> hotelAdvertisers, List<AdvertiserDto> advertiserList,
            HotelDto hotelDto, SearchItemDto searchItemDto) {

        List<Offer> offerList = new ArrayList<>();

        hotelAdvertisers.parallelStream().forEach(ad ->  {
            if (ad.getHotelId().equals(hotelDto.getId()) &&
                    searchItemDto.getStartDate().after(ad.getAvailabilityStartDate()) &&
                    searchItemDto.getStartDate().before(ad.getAvailabilityEndDate())) {
                Offer offer = mapper.toOffer(ad);
                offer.setAdvertiser(findAdvertiserNameById(advertiserList, ad.getAdvertiserId()));
                offerList.add(offer);
            }
        });
        return offerList;
    }
}
