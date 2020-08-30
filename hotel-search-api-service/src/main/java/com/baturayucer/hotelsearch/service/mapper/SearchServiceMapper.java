package com.baturayucer.hotelsearch.service.mapper;

import com.baturayucer.hotelsearch.data.model.AdvertiserEntity;
import com.baturayucer.hotelsearch.data.model.CityEntity;
import com.baturayucer.hotelsearch.data.model.HotelAdvertiserEntity;
import com.baturayucer.hotelsearch.data.model.HotelEntity;
import com.baturayucer.hotelsearch.service.model.AdvertiserDto;
import com.baturayucer.hotelsearch.service.model.CityDto;
import com.baturayucer.hotelsearch.service.model.HotelAdvertiserDto;
import com.baturayucer.hotelsearch.service.model.HotelDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SearchServiceMapper {

    SearchServiceMapper INSTANCE = Mappers.getMapper( SearchServiceMapper.class );

    List<AdvertiserDto> toAdvertiserDtoList(List<AdvertiserEntity> advertiserEntities);
    List<CityDto> toCityDtoList(List<CityEntity> cityEntities);
    List<HotelAdvertiserDto> toHotelAdvertiserDtoList(List<HotelAdvertiserEntity> hotelAdvertiserEntities);
    List<HotelDto> toHotelDtoList(List<HotelEntity> hotelEntities);
}