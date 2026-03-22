package com.fiap.hackaton.sus.helper.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {

    private Long id;
    private String street;
    private String neighborhood;
    private String zipCode;
    private int number;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
