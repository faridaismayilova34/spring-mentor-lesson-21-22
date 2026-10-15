package org.example.springles21.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TalkRequestDto {
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Room cannot be blank")
    private String room;

    @NotNull(message = "Duration is required")
    @Min(value = 15, message = "Duration must be at least 15 minutes")
    @Max(value = 90, message = "Duration must be at most 90 minutes")
    private Integer durationMinutes;

    @NotNull(message = "Scheduled time is required")
    private LocalDateTime scheduledAt;

    @NotNull(message = "Speaker ID is required")
    private Long speakerId;
}