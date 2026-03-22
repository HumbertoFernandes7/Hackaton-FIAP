package com.fiap.hackaton.sus.helper.controllers;

import com.fiap.hackaton.sus.helper.dtos.HealthUnitRequestDTO;
import com.fiap.hackaton.sus.helper.dtos.HealthUnitResponseDTO;
import com.fiap.hackaton.sus.helper.entities.healthUnitEntity;
import com.fiap.hackaton.sus.helper.mappers.HealthUnitMapper;
import com.fiap.hackaton.sus.helper.services.healthUnitService;
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
@RequestMapping("/health-units")
@RequiredArgsConstructor
public class HealthUnitController {

    private final healthUnitService healthUnitService;
    private final HealthUnitMapper healthUnitMapper;

    @PostMapping
    public ResponseEntity<HealthUnitResponseDTO> create(@Valid @RequestBody HealthUnitRequestDTO requestDTO) {
        healthUnitEntity entity = healthUnitMapper.toEntity(requestDTO);
        healthUnitEntity savedEntity = healthUnitService.create(entity);
        HealthUnitResponseDTO responseDTO = healthUnitMapper.toResponse(savedEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<HealthUnitResponseDTO>> findAll() {
        List<HealthUnitResponseDTO> response = healthUnitService.findAll()
                .stream()
                .map(healthUnitMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthUnitResponseDTO> findById(@PathVariable UUID id) {
        healthUnitEntity entity = healthUnitService.findById(id);
        HealthUnitResponseDTO response = healthUnitMapper.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthUnitResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody HealthUnitRequestDTO requestDTO
    ) {
        healthUnitEntity entity = healthUnitService.findById(id);
        healthUnitMapper.copyToEntity(requestDTO, entity);
        healthUnitEntity updatedEntity = healthUnitService.update(entity);
        HealthUnitResponseDTO response = healthUnitMapper.toResponse(updatedEntity);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        healthUnitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
