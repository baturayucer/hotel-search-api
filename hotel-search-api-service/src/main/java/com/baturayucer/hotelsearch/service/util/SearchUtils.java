package com.baturayucer.hotelsearch.service.util;

import com.baturayucer.hotelsearch.service.exception.AdvertSearchException;
import com.baturayucer.hotelsearch.service.mapper.SearchServiceMapper;
import com.baturayucer.hotelsearch.service.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Util methods for searching and sorting adverts.
 * @author baturayucer.
 */
public final class SearchUtils {

    private static final SearchServiceMapper mapper = SearchServiceMapper.INSTANCE;

    public static List<HotelDto> filterHotelsByCity(List<HotelDto> hotels, List<CityDto> cityList) {

        return hotels.parallelStream().filter(hotelDto -> {
            String foundCityId = findCityByHotel(cityList, hotelDto).getId();
            return hotelDto.getCityId().equals(foundCityId);
        }).collect(Collectors.toList());
    }

    public static CityDto findCityByHotel(List<CityDto> cities, HotelDto hotelDto) {

        return cities.parallelStream().
                filter(cityDto -> cityDto.getId().equals(hotelDto.getCityId()))
                .findAny().orElseThrow(() ->
                        new AdvertSearchException("City could not found with given parameter"));
    }

    public static String findAdvertiserNameById(List<AdvertiserDto> advertisers, String advertiserId) {

        return advertisers.parallelStream()
                .filter(advert -> advert.getId().equals(advertiserId))
                .findAny().orElseThrow(() ->
                        new AdvertSearchException("Advertiser Name could not found with given Id"))
                .getAdvertiserName();
    }

    public static List<Offer> findAvailableHotelOffers(
            List<HotelAdvertiserDto> hotelAdvertisers, List<AdvertiserDto> advertiserList,
            HotelDto hotelDto, SearchItemDto searchItemDto) {

        List<Offer> offerList = new ArrayList<>();

        Predicate<HotelAdvertiserDto> areDatesAvailable = ad ->
                searchItemDto.getStartDate().after(ad.getAvailabilityStartDate()) &&
                searchItemDto.getStartDate().before(ad.getAvailabilityEndDate());

        Predicate<HotelAdvertiserDto> isHotelSame = ad
                -> ad.getHotelId().equals(hotelDto.getId());

        Predicate<HotelAdvertiserDto> isHotelAvailable = ad
                -> areDatesAvailable.test(ad) && isHotelSame.test(ad);

        hotelAdvertisers.parallelStream().filter(isHotelAvailable)
                .forEach(ad -> offerList.add(
                        prepareOfferByAdvertiser(advertiserList, ad)));
        return offerList;
    }

    private static Offer prepareOfferByAdvertiser(List<AdvertiserDto> adverts,
                                           HotelAdvertiserDto hotelAdvertiserDto) {

        String advertiserName =
                findAdvertiserNameById(adverts, hotelAdvertiserDto.getAdvertiserId());
        Offer offer = mapper.toOffer(hotelAdvertiserDto);
        offer.setAdvertiser(advertiserName);
        return offer;
    }

    private SearchUtils() {

        throw new IllegalStateException("This is a utility class.");
    }
}
