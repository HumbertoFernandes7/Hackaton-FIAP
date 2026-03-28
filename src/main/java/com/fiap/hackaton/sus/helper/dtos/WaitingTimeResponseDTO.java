package com.fiap.hackaton.sus.helper.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class WaitingTimeResponseDTO {

    private Long id;
    private HealthUnitResponseDTO healthUnitId;
    private int waitingTimeMinutes;
    private int bluePatients;
    private int greenPatients;
    private int yellowPatients;
    private int redPatients;
    private int blueWaitingTime;
    private int greenWaitingTime;
    private int yellowWaitingTime;
    private int redWaitingTime;
    private Instant createdAt;
    private Instant updatedAt;
}
