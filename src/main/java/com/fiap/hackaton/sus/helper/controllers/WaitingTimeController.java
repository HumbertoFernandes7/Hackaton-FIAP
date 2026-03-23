package com.fiap.hackaton.sus.helper.controllers;

import com.fiap.hackaton.sus.helper.dtos.WaitingTimeRequestDTO;
import com.fiap.hackaton.sus.helper.dtos.WaitingTimeResponseDTO;
import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import com.fiap.hackaton.sus.helper.entities.WaitingTimeEntity;
import com.fiap.hackaton.sus.helper.mappers.WaitingTimeMapper;
import com.fiap.hackaton.sus.helper.services.HealthUnitService;
import com.fiap.hackaton.sus.helper.services.WaitingTimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/waiting-time")
@RequiredArgsConstructor
public class WaitingTimeController {

    private final WaitingTimeService waitingTimeService;
    private final HealthUnitService healthUnitService;
    private final WaitingTimeMapper waitingTimeMapper;

    @PostMapping("/{healthUnitId}")
    public ResponseEntity<WaitingTimeResponseDTO> create(
            @Valid @RequestBody WaitingTimeRequestDTO requestDTO,
            @PathVariable UUID healthUnitId) {

        HealthUnitEntity healthUnitEntity = healthUnitService.findById(healthUnitId);
        WaitingTimeEntity entity = waitingTimeMapper.toEntity(requestDTO, healthUnitEntity);
        WaitingTimeEntity savedEntity = waitingTimeService.create(entity);
        WaitingTimeResponseDTO response = waitingTimeMapper.toResponse(savedEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<WaitingTimeResponseDTO>> findAll() {
        List<WaitingTimeEntity> entities = waitingTimeService.findAll();
        List<WaitingTimeResponseDTO> response = waitingTimeMapper.toListResponse(entities);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaitingTimeResponseDTO> findById(@PathVariable Long id) {
        WaitingTimeEntity entity = waitingTimeService.findById(id);
        WaitingTimeResponseDTO response = waitingTimeMapper.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{healthUnitId}")
    public ResponseEntity<WaitingTimeResponseDTO> updateByHealthUnit(
            @PathVariable UUID healthUnitId,
            @Valid @RequestBody WaitingTimeRequestDTO requestDTO
    ) {
        HealthUnitEntity healthUnitEntity = healthUnitService.findById(healthUnitId);
        WaitingTimeEntity entity = waitingTimeService.findByHealthUnitId(healthUnitEntity);
        waitingTimeMapper.copyToEntity(requestDTO, entity);
        WaitingTimeEntity updatedEntity = waitingTimeService.update(entity);
        WaitingTimeResponseDTO response = waitingTimeMapper.toResponse(updatedEntity);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        waitingTimeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
