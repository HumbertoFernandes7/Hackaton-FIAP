package com.fiap.hackaton.sus.helper.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WaitingTimeResponseDTO {

    private Long id;
    private UUID healthUnitId;
    private int waitingTimeMinutes;
    private int waitingPatients;
    private Instant createdAt;
    private Instant updatedAt;
}
