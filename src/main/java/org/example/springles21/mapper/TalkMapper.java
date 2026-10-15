package org.example.springles21.mapper;

import org.example.springles21.dto.request.TalkRequestDto;
import org.example.springles21.dto.response.TalkResponseDto;
import org.example.springles21.entity.Talk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TalkMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "speaker", ignore = true)
    @Mapping(target = "status", ignore = true)
    Talk toEntity(TalkRequestDto requestDto);

    @Mapping(source = "speaker.id", target = "speakerId")
    @Mapping(source = "speaker.fullName", target = "speakerName")
    TalkResponseDto toDto(Talk talk);

    List<TalkResponseDto> toDtoList(List<Talk> talks);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "speaker", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateTalkFromDto(TalkRequestDto requestDto, @MappingTarget Talk talk);
}
