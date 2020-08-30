package com.baturayucer.hotelsearch.data.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Database {

    private List<AdvertiserEntity> advertisers;
    private List<CityEntity> cities;
    private List<HotelAdvertiserEntity> hotelAdvertisers;
    private List<HotelEntity> hotels;
}
