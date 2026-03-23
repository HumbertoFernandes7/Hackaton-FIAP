package com.fiap.hackaton.sus.helper.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WaitingTimeRequestDTO {

    @PositiveOrZero(message = "Waiting time minutes must be greater than or equal to zero")
    private int waitingTimeMinutes;

    @PositiveOrZero(message = "Waiting patients must be greater than or equal to zero")
    private int waitingPatients;
}
