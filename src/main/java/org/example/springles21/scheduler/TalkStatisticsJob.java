package org.example.springles21.scheduler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springles21.entity.Talk;
import org.example.springles21.repository.SpeakerRepository;
import org.example.springles21.repository.TalkRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class TalkStatisticsJob {
    private final SpeakerRepository speakerRepository;
    private final TalkRepository talkRepository;

    @Scheduled(fixedRateString = "${conference.report.rate-ms}", initialDelay = 10000)
    public void reportStatistics() {
        try {
            log.debug("Statistics job started");
            long totalSpeakers = speakerRepository.count();
            long totalTalks = talkRepository.count();
            log.info("Total speakers: {}, Total talks: {}", totalSpeakers, totalTalks);

            List<Object[]> roomCounts = talkRepository.countTalksPerRoom();
            for (Object[] row : roomCounts) {
                log.info("Room: {}, Talks count: {}", row[0], row[1]);
            }
        } catch (Exception ex) {
            log.error("Statistics job failed", ex);
        }
    }
}




















//    private final SpeakerRepository speakerRepository;
//    private final TalkRepository talkRepository;
//
//    @Scheduled(fixedRateString = "${conference.report.rate-ms}", initialDelay = 10000)
//    public void reportStatistics() {
//        try {
//            log.debug("Statistics job started");
//
//            long totalSpeakers = speakerRepository.count();
//            long totalTalks = talkRepository.count();
//            log.info("Total speakers: {}, Total talks: {}", totalSpeakers, totalTalks);
//
//            List<Object[]> talksPerRoom = talkRepository.countTalksPerRoom();
//            for (Object[] row : talksPerRoom) {
//                String room = (String) row[0];
//                Long count = (Long) row[1];
//                log.info("Room '{}': {} talks", room, count);
//            }
//        } catch (Exception ex) {
//            log.error("Statistics job failed", ex);
//        }
