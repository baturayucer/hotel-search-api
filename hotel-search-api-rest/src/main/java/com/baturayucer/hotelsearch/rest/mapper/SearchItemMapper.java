package com.baturayucer.hotelsearch.rest.mapper;

import com.baturayucer.hotelsearch.rest.model.SearchItemResponse;
import com.baturayucer.hotelsearch.service.model.SearchItemDto;
import com.baturayucer.hotelsearch.service.model.SearchOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper
public interface SearchItemMapper {

    SearchItemMapper INSTANCE = Mappers.getMapper( SearchItemMapper.class );

    @Mapping(source = "city", target = "city")
    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "stringToDate")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "stringToDate")
    SearchItemDto toItemDto(String city, String startDate, String endDate);

    List<SearchItemResponse> toItemResponse(List<SearchOutputDto> searchOutputDto);

    @Named("stringToDate")
    default Date stringToDate(String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.parse(date);
    }
}