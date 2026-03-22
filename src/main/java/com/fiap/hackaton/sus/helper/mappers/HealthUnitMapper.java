package com.fiap.hackaton.sus.helper.mappers;

import com.fiap.hackaton.sus.helper.dtos.HealthUnitRequestDTO;
import com.fiap.hackaton.sus.helper.dtos.HealthUnitResponseDTO;
import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HealthUnitMapper {

    private final ModelMapper modelMapper;
    private final AddressMapper addressMapper;

    public HealthUnitEntity toEntity(HealthUnitRequestDTO requestDTO) {
        HealthUnitEntity entity = modelMapper.map(requestDTO, HealthUnitEntity.class);
        entity.setAddressId(addressMapper.toEntity(requestDTO.getAddress()));
        return entity;
    }

    public HealthUnitResponseDTO toResponse(HealthUnitEntity entity) {
        HealthUnitResponseDTO responseDTO = modelMapper.map(entity, HealthUnitResponseDTO.class);
        responseDTO.setAddress(addressMapper.toResponse(entity.getAddressId()));
        return responseDTO;
    }

    public void copyToEntity(HealthUnitRequestDTO requestDTO, HealthUnitEntity entity) {
        modelMapper.map(requestDTO, entity);
        entity.setAddressId(addressMapper.toEntity(requestDTO.getAddress()));
    }

    public List<HealthUnitResponseDTO> toListResponse(List<HealthUnitEntity> listEntity) {
        return listEntity.stream()
                .map(this::toResponse).collect(Collectors.toList());
    }
}
