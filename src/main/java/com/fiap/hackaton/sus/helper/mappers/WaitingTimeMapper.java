package com.fiap.hackaton.sus.helper.mappers;

import com.fiap.hackaton.sus.helper.dtos.WaitingTimeRequestDTO;
import com.fiap.hackaton.sus.helper.dtos.WaitingTimeResponseDTO;
import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import com.fiap.hackaton.sus.helper.entities.WaitingTimeEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WaitingTimeMapper {

    private final ModelMapper modelMapper;

    public WaitingTimeEntity toEntity(WaitingTimeRequestDTO waitingTimeRequestDTO, HealthUnitEntity healthUnitEntity) {
        WaitingTimeEntity entity = modelMapper.map(waitingTimeRequestDTO, WaitingTimeEntity.class);
        entity.setHealthUnitId(healthUnitEntity);
        return entity;
    }

    public WaitingTimeResponseDTO toResponse(WaitingTimeEntity waitingTimeEntity) {
        return modelMapper.map(waitingTimeEntity, WaitingTimeResponseDTO.class);
    }

    public void copyToEntity(WaitingTimeRequestDTO requestDTO, WaitingTimeEntity entity) {
        modelMapper.map(requestDTO, entity);
    }

    public List<WaitingTimeResponseDTO> toListResponse(List<WaitingTimeEntity> entities) {
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
