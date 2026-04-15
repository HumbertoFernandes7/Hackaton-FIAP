package com.fiap.hackaton.sus.helper.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {

    @NotBlank(message = "Street is required")
    @Size(max = 150, message = "Street must have at most 150 characters")
    private String street;

    @NotBlank(message = "Neighborhood is required")
    @Size(max = 100, message = "Neighborhood must have at most 100 characters")
    private String neighborhood;

    @NotBlank(message = "Zip code is required")
    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "Zip code must be in CEP format: 00000-000 or 00000000")
    private String zipCode;

    @Positive(message = "Number must be greater than zero")
    private int number;
}
