package com.baturayucer.hotelsearch.rest.mapper;

import com.baturayucer.hotelsearch.rest.model.SearchItemRequest;
import com.baturayucer.hotelsearch.rest.model.SearchItemResponse;
import com.baturayucer.hotelsearch.service.model.SearchItemDto;
import com.baturayucer.hotelsearch.service.model.SearchOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SearchItemMapper {

    SearchItemMapper INSTANCE = Mappers.getMapper( SearchItemMapper.class );

    SearchItemDto toItemDto(SearchItemRequest searchItemRequest);
    SearchItemResponse toItemResponse(SearchOutputDto searchOutputDto);
}