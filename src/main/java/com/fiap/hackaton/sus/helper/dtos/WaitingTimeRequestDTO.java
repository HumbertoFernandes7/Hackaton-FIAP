package com.fiap.hackaton.sus.helper.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class WaitingTimeRequestDTO {

    @NotNull(message = "Overall waiting time minutes cannot be null")
    @PositiveOrZero(message = "Overall waiting time minutes must be greater than or equal to zero")
    private Integer waitingTimeMinutes;

    @NotNull(message = "Blue patients cannot be null")
    @PositiveOrZero(message = "Blue patients must be greater than or equal to zero")
    private Integer bluePatients;

    @NotNull(message = "Green patients cannot be null")
    @PositiveOrZero(message = "Green patients must be greater than or equal to zero")
    private Integer greenPatients;

    @NotNull(message = "Yellow patients cannot be null")
    @PositiveOrZero(message = "Yellow patients must be greater than or equal to zero")
    private Integer yellowPatients;

    @NotNull(message = "Red patients cannot be null")
    @PositiveOrZero(message = "Red patients must be greater than or equal to zero")
    private Integer redPatients;

    @NotNull(message = "Blue waiting time cannot be null")
    @PositiveOrZero(message = "Blue waiting time must be greater than or equal to zero")
    private Integer blueWaitingTime;

    @NotNull(message = "Green waiting time cannot be null")
    @PositiveOrZero(message = "Green waiting time must be greater than or equal to zero")
    private Integer greenWaitingTime;

    @NotNull(message = "Yellow waiting time cannot be null")
    @PositiveOrZero(message = "Yellow waiting time must be greater than or equal to zero")
    private Integer yellowWaitingTime;

    @NotNull(message = "Red waiting time cannot be null")
    @PositiveOrZero(message = "Red waiting time must be greater than or equal to zero")
    private Integer redWaitingTime;
}
