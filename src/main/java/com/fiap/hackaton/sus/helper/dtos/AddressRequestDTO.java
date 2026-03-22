package com.fiap.hackaton.sus.helper.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {

    private String street;
    private String neighborhood;
    private String zipCode;
    private int number;
}
