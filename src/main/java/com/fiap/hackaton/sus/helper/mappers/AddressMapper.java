package com.fiap.hackaton.sus.helper.mappers;

import com.fiap.hackaton.sus.helper.dtos.AddressRequestDTO;
import com.fiap.hackaton.sus.helper.dtos.AddressResponseDTO;
import com.fiap.hackaton.sus.helper.entities.AddressEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapper {

    private final ModelMapper modelMapper;

    public AddressEntity toEntity(AddressRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, AddressEntity.class);
    }

    public AddressResponseDTO toResponse(AddressEntity entity) {
        return modelMapper.map(entity, AddressResponseDTO.class);
    }

    public void updateEntityFromRequest(AddressRequestDTO requestDTO, AddressEntity entity) {
        modelMapper.map(requestDTO, entity);
    }
}
