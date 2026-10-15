package org.example.springles21.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springles21.dto.request.TalkRequestDto;
import org.example.springles21.dto.response.TalkResponseDto;
import org.example.springles21.service.TalkService;
import org.example.springles21.service.impl.TalkServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/talks")
@RequiredArgsConstructor
public class TalkController {

    private final TalkService talkService;

    @PostMapping
    public ResponseEntity<TalkResponseDto> createTalk(@Valid @RequestBody TalkRequestDto requestDto) {
        return new ResponseEntity<>(talkService.createTalk(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TalkResponseDto>> getAllTalks() {
        return ResponseEntity.ok(talkService.getAllTalks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TalkResponseDto> getTalksById(@PathVariable Long id) {
        return ResponseEntity.ok(talkService.getTalkById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TalkResponseDto> updateTalk(@PathVariable Long id, @Valid @RequestBody TalkRequestDto requestDto) {
        return ResponseEntity.ok(talkService.updateTalk(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTalk(@PathVariable Long id) {
        talkService.deleteTalk(id);
        return ResponseEntity.noContent().build();
    }
}
