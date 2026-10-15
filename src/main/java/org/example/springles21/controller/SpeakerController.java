package org.example.springles21.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springles21.dto.request.SpeakerRequestDto;
import org.example.springles21.dto.response.SpeakerResponseDto;
import org.example.springles21.dto.response.TalkResponseDto;
import org.example.springles21.service.SpeakerService;
import org.example.springles21.service.impl.SpeakerServiceImpl;
import org.example.springles21.service.impl.TalkServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/speakers")
@RequiredArgsConstructor
public class SpeakerController {

    private final SpeakerService speakerService;

    @PostMapping
    public ResponseEntity<SpeakerResponseDto> createSpeaker(@Valid @RequestBody SpeakerRequestDto requestDto) {
        return new ResponseEntity<>(speakerService.createSpeaker(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SpeakerResponseDto>> getAllSpeakers() {
        return ResponseEntity.ok(speakerService.getAllSpeakers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpeakerResponseDto> getSpeakerById(@PathVariable Long id) {
        return ResponseEntity.ok(speakerService.getSpeakerById(id));
    }

    @GetMapping("/{id}/talks")
    public ResponseEntity<List<TalkResponseDto>> getTalksBySpeakerId(@PathVariable Long id) {
        return ResponseEntity.ok(speakerService.getTalksBySpeakerId(id));
    }
}





















//
//    private final SpeakerServiceImpl speakerService;
//    private final TalkServiceImpl talkService;
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public SpeakerResponseDto create(@Valid @RequestBody SpeakerRequestDto dto) {
//        return speakerService.create(dto);
//    }
//
//
//    @GetMapping
//    public List<SpeakerResponseDto> findAll() {
//        return speakerService.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public SpeakerResponseDto findById(@PathVariable Long id) {
//        return speakerService.findById(id);
//    }
//
//    @GetMapping("/{id}/talks")
//    public List<TalkResponseDto> findTalksBySpeakerId(@PathVariable Long id) {
//        return talkService.findBySpeakerId(id);
//    }
//}