package com.fiap.hackaton.sus.helper.dtos;

import com.fiap.hackaton.sus.helper.enums.UnitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HealthUnitResponseDTO {

    private UUID id;
    private UnitType unitType;
    private String name;
    private AddressResponseDTO address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private boolean isActive;
    private boolean open24h;
    private LocalTime openTime;
    private LocalTime closeTime;
    private int maxCapacity;
    private Instant createdAt;
    private Instant updatedAt;
}
