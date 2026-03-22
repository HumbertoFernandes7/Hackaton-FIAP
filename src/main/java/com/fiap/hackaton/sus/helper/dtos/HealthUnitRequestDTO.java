package com.fiap.hackaton.sus.helper.dtos;

import com.fiap.hackaton.sus.helper.enums.UnitType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HealthUnitRequestDTO {

    @NotNull(message = "Unit type is required")
    private UnitType unitType;

    @NotBlank(message = "Name is required")
    @Size(max = 120, message = "Name must have at most 120 characters")
    private String name;

    @NotNull(message = "Address is required")
    @Valid
    private AddressRequestDTO address;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "Latitude must be <= 90")
    private BigDecimal latitude;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "Longitude must be <= 180")
    private BigDecimal longitude;

    @NotNull(message = "Active is required")
    private Boolean active;

    @NotNull(message = "Open 24h is required")
    private Boolean open24h;

    private LocalTime openTime;

    private LocalTime closeTime;

    @Positive(message = "Max capacity must be greater than zero")
    private int maxCapacity;

    @AssertTrue(message = "Open time and close time are required when open24h is false")
    public boolean isOperatingHoursValid() {
        if (open24h == null || open24h) {
            return true;
        }
        return openTime != null && closeTime != null;
    }
}
