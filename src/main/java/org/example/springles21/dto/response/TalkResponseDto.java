package org.example.springles21.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TalkResponseDto {
    private Long id;
    private String title;
    private String room;
    private Integer durationMinutes;
    private Long speakerId;
    private String speakerName;
}