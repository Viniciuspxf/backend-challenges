package br.com.vibbra.hourglass.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeRequestDTO {
    private int projectId;

    private int userId;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

}
