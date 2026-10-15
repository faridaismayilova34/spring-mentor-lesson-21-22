package org.example.springles21.mapper;

import org.example.springles21.dto.request.SpeakerRequestDto;
import org.example.springles21.dto.response.SpeakerResponseDto;
import org.example.springles21.entity.Speaker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpeakerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "talks", ignore = true)
    Speaker toEntity(SpeakerRequestDto dto);

    SpeakerResponseDto toDto(Speaker entity);
    List<SpeakerResponseDto> toDtoList(List<Speaker> entities);
}