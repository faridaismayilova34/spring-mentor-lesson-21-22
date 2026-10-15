package org.example.springles21.service;

import org.example.springles21.dto.request.SpeakerRequestDto;
import org.example.springles21.dto.response.SpeakerResponseDto;
import org.example.springles21.dto.response.TalkResponseDto;

import java.util.List;

public interface SpeakerService {
    SpeakerResponseDto createSpeaker(SpeakerRequestDto requestDto);
    List<SpeakerResponseDto> getAllSpeakers();
    SpeakerResponseDto getSpeakerById(Long id);
    List<TalkResponseDto> getTalksBySpeakerId(Long speakerId);
}




