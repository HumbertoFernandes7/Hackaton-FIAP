package com.fiap.hackaton.sus.helper.mappers;

import com.fiap.hackaton.sus.helper.dtos.HealthUnitRequestDTO;
import com.fiap.hackaton.sus.helper.dtos.HealthUnitResponseDTO;
import com.fiap.hackaton.sus.helper.entities.healthUnitEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HealthUnitMapper {

    private final ModelMapper modelMapper;
    private final AddressMapper addressMapper;

    public healthUnitEntity toEntity(HealthUnitRequestDTO requestDTO) {
        healthUnitEntity entity = modelMapper.map(requestDTO, healthUnitEntity.class);
        entity.setAddressId(addressMapper.toEntity(requestDTO.getAddress()));
        return entity;
    }

    public HealthUnitResponseDTO toResponse(healthUnitEntity entity) {
        HealthUnitResponseDTO responseDTO = modelMapper.map(entity, HealthUnitResponseDTO.class);
        responseDTO.setAddress(addressMapper.toResponse(entity.getAddressId()));
        return responseDTO;
    }

    public void copyToEntity(HealthUnitRequestDTO requestDTO, healthUnitEntity entity) {
        modelMapper.map(requestDTO, entity);
        entity.setAddressId(addressMapper.toEntity(requestDTO.getAddress()));
    }
}
