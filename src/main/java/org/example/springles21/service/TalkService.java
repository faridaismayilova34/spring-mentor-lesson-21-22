package org.example.springles21.service;

import org.example.springles21.dto.request.TalkRequestDto;
import org.example.springles21.dto.response.TalkResponseDto;

import java.util.List;

public interface TalkService {
    TalkResponseDto createTalk(TalkRequestDto requestDto);
    List<TalkResponseDto> getAllTalks();
    TalkResponseDto getTalkById(Long id);
    TalkResponseDto updateTalk(Long id, TalkRequestDto requestDto);
    void deleteTalk(Long id);
}
