package com.fiap.hackaton.sus.helper.controllers;

import com.fiap.hackaton.sus.helper.dtos.HealthUnitRequestDTO;
import com.fiap.hackaton.sus.helper.dtos.HealthUnitResponseDTO;
import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import com.fiap.hackaton.sus.helper.mappers.HealthUnitMapper;
import com.fiap.hackaton.sus.helper.services.GoogleMapsService;
import com.fiap.hackaton.sus.helper.services.HealthUnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/health-units")
@RequiredArgsConstructor
public class HealthUnitController {

    private final HealthUnitService healthUnitService;
    private final HealthUnitMapper healthUnitMapper;
    private final GoogleMapsService googleMapsService;

    @PostMapping
    public ResponseEntity<HealthUnitResponseDTO> create(@Valid @RequestBody HealthUnitRequestDTO requestDTO) {
        HealthUnitEntity entity = healthUnitMapper.toEntity(requestDTO);
        HealthUnitEntity savedEntity = healthUnitService.create(entity);
        HealthUnitResponseDTO responseDTO = healthUnitMapper.toResponse(savedEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/best-by-user-location")
    public ResponseEntity<List<HealthUnitResponseDTO>> getBestByUserLocation(@RequestParam String userLocation) {
        List<HealthUnitEntity> bestByLocation = healthUnitService.getBestByLocation(userLocation);
        return ResponseEntity.ok(healthUnitMapper.toListResponse(bestByLocation));
    }

    @GetMapping
    public ResponseEntity<List<HealthUnitResponseDTO>> findAll() {
        List<HealthUnitEntity> listEntity = healthUnitService.findAll();
        List<HealthUnitResponseDTO> listResponse = healthUnitMapper.toListResponse(listEntity);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthUnitResponseDTO> findById(@PathVariable UUID id) {
        HealthUnitEntity entity = healthUnitService.findById(id);
        HealthUnitResponseDTO response = healthUnitMapper.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/travel")
    public ResponseEntity<String> getTimeToTravel(@RequestParam String origin,
                                                  @RequestParam String destination,
                                                  @RequestParam String healthUnitName){
        return ResponseEntity.ok(googleMapsService.calculateTravelTime(origin, destination, healthUnitName));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthUnitResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody HealthUnitRequestDTO requestDTO
    ) {
        HealthUnitEntity entity = healthUnitService.findById(id);
        healthUnitMapper.copyToEntity(requestDTO, entity);
        HealthUnitEntity updatedEntity = healthUnitService.update(entity);
        HealthUnitResponseDTO response = healthUnitMapper.toResponse(updatedEntity);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        healthUnitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
