package org.example.springles21.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SpeakerResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String company;
}
