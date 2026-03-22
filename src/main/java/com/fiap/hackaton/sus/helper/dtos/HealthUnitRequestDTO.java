package com.fiap.hackaton.sus.helper.dtos;

import com.fiap.hackaton.sus.helper.enums.UnitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HealthUnitRequestDTO {

    private UnitType unitType;
    private String name;
    private AddressRequestDTO address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private boolean isActive;
    private boolean open24h;
    private int maxCapacity;
}
