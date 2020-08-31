package com.baturayucer.hotelsearch.service.mapper;

import com.baturayucer.hotelsearch.data.model.AdvertiserEntity;
import com.baturayucer.hotelsearch.data.model.CityEntity;
import com.baturayucer.hotelsearch.data.model.HotelAdvertiserEntity;
import com.baturayucer.hotelsearch.data.model.HotelEntity;
import com.baturayucer.hotelsearch.service.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Mapper for SearchService.
 * @author baturayucer.
 */
@Mapper
public interface SearchServiceMapper {

    SearchServiceMapper INSTANCE = Mappers.getMapper( SearchServiceMapper.class );

    List<AdvertiserDto> toAdvertiserDtoList(List<AdvertiserEntity> advertiserEntities);
    List<CityDto> toCityDtoList(List<CityEntity> cityEntities);
    List<HotelDto> toHotelDtoList(List<HotelEntity> hotelEntities);
    List<HotelAdvertiserDto> toHotelAdvertiserDtoList(List<HotelAdvertiserEntity> hotelAdvertiserEntities);

    default Date stringToDate(String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.parse(date);
    }

    @Mapping(source = "id", target = "hotelId")
    @Mapping(source = "name", target = "hotelName")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "stars", target = "stars")
    SearchOutputDto toSearchOutputDto(HotelDto hotelDto);

    Offer toOffer(HotelAdvertiserDto hotelAdvertiserDto);
}