package org.example.springles21.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springles21.dto.request.TalkRequestDto;
import org.example.springles21.dto.response.TalkResponseDto;
import org.example.springles21.entity.Speaker;
import org.example.springles21.entity.Talk;
import org.example.springles21.exception.BusinessRuleException;
import org.example.springles21.exception.ResourceNotFoundException;
import org.example.springles21.mapper.TalkMapper;
import org.example.springles21.repository.SpeakerRepository;
import org.example.springles21.repository.TalkRepository;
import org.example.springles21.service.TalkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TalkServiceImpl implements TalkService {

    private final TalkRepository talkRepository;
    private final SpeakerRepository speakerRepository;
    private final TalkMapper talkMapper;

    private Talk findTalkOrThrow(Long id) {
        return talkRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Talk with id {} not found", id);
                    return new ResourceNotFoundException("Talk with id " + id + " not found");
                });
    }


    private Speaker findSpeakerOrThrow(Long speakerId) {
        return speakerRepository.findById(speakerId)
                .orElseThrow(() -> {
                    log.warn("Speaker with id {} not found", speakerId);
                    return new ResourceNotFoundException("Speaker with id " + speakerId + " not found");
                });
    }


    private void validateDuration(Integer durationMinutes) {
        if (durationMinutes < 15 || durationMinutes > 90) {
            log.warn("Talk duration {} is invalid", durationMinutes);
            throw new BusinessRuleException("A talk lasts between 15 and 90 minutes");
        }
    }

    @Override
    @Transactional
    public TalkResponseDto createTalk(TalkRequestDto requestDto) {
        Speaker speaker = findSpeakerOrThrow(requestDto.getSpeakerId());

        long talkCount = talkRepository.countBySpeakerId(speaker.getId());
        if (talkCount >= 3) {
            log.warn("Speaker {} already has {} talks", speaker.getId(), talkCount);
            throw new BusinessRuleException("A speaker can have at most 3 talks");
        }

        validateDuration(requestDto.getDurationMinutes());

        Talk talk = talkMapper.toEntity(requestDto);
        talk.setSpeaker(speaker);
        Talk saved = talkRepository.save(talk);
        log.info("Talk created with id {}", saved.getId());
        return talkMapper.toDto(saved);
    }

    @Override
    public List<TalkResponseDto> getAllTalks() {
        log.debug("Fetching all talks");
        return talkMapper.toDtoList(talkRepository.findAll());
    }

    @Override
    public TalkResponseDto getTalkById(Long id) {
        log.debug("Fetching talk {}", id);
        return talkMapper.toDto(findTalkOrThrow(id));
    }

    @Override
    @Transactional
    public TalkResponseDto updateTalk(Long id, TalkRequestDto requestDto) {
        log.debug("Updating talk {}", id);
        Talk talk = findTalkOrThrow(id);
        Speaker speaker = findSpeakerOrThrow(requestDto.getSpeakerId());

        validateDuration(requestDto.getDurationMinutes());

        talkMapper.updateTalkFromDto(requestDto, talk);
        talk.setSpeaker(speaker);
        Talk updated = talkRepository.save(talk);
        log.info("Talk updated with id {}", updated.getId());
        return talkMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteTalk(Long id) {
        log.debug("Deleting talk {}", id);
        Talk talk = findTalkOrThrow(id);
        talkRepository.delete(talk);
        log.info("Talk deleted with id {}", id);
    }
}
