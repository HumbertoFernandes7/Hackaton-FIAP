package com.fiap.hackaton.sus.helper.mappers;

import com.fiap.hackaton.sus.helper.dtos.WaitingTimeRequestDTO;
import com.fiap.hackaton.sus.helper.dtos.WaitingTimeResponseDTO;
import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import com.fiap.hackaton.sus.helper.entities.WaitingTimeEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WaitingTimeMapper {

    public WaitingTimeEntity toEntity(WaitingTimeRequestDTO waitingTimeRequestDTO) {
        WaitingTimeEntity entity = new WaitingTimeEntity();
        copyToEntity(waitingTimeRequestDTO, entity);
        return entity;
    }

    public WaitingTimeResponseDTO toResponse(WaitingTimeEntity waitingTimeEntity) {
        WaitingTimeResponseDTO responseDTO = new WaitingTimeResponseDTO();
        responseDTO.setId(waitingTimeEntity.getId());
        responseDTO.setWaitingTimeMinutes(waitingTimeEntity.getWaitingTimeMinutes());
        responseDTO.setWaitingPatients(waitingTimeEntity.getWaitingPatients());
        responseDTO.setCreatedAt(waitingTimeEntity.getCreatedAt());
        responseDTO.setUpdatedAt(waitingTimeEntity.getUpdatedAt());
        if (waitingTimeEntity.getHealthUnitId() != null) {
            responseDTO.setHealthUnitId(waitingTimeEntity.getHealthUnitId().getId());
        }
        return responseDTO;
    }

    public void copyToEntity(WaitingTimeRequestDTO requestDTO, WaitingTimeEntity entity) {

        if (requestDTO.getHealthUnitId() == null) {
            entity.setHealthUnitId(null);
            return;
        }

        HealthUnitEntity healthUnit = entity.getHealthUnitId();
        if (healthUnit == null) {
            healthUnit = new HealthUnitEntity();
        }
        healthUnit.setId(requestDTO.getHealthUnitId());
        entity.setHealthUnitId(healthUnit);
    }

    public List<WaitingTimeResponseDTO> toListResponse(List<WaitingTimeEntity> entities) {
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
