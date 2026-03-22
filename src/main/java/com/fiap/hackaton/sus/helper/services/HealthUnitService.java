package com.fiap.hackaton.sus.helper.services;

import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import com.fiap.hackaton.sus.helper.repositories.AddressEntityRepository;
import com.fiap.hackaton.sus.helper.repositories.HealthUnitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HealthUnitService {

    private final HealthUnitRepository healthUnitRepository;
    private final AddressEntityRepository addressEntityRepository;

    @Transactional
    public HealthUnitEntity create(HealthUnitEntity entity) {
        addressEntityRepository.save(entity.getAddressId());
        return healthUnitRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<HealthUnitEntity> findAll() {
        return healthUnitRepository.findAll();
    }

    @Transactional(readOnly = true)
    public HealthUnitEntity findById(UUID id) {
        return getByIdOrThrow(id);
    }

    @Transactional
    public HealthUnitEntity update(HealthUnitEntity entity) {
        getByIdOrThrow(entity.getId());
        addressEntityRepository.save(entity.getAddressId());
        return healthUnitRepository.save(entity);
    }

    @Transactional
    public void delete(UUID id) {
        HealthUnitEntity entity = getByIdOrThrow(id);
        healthUnitRepository.delete(entity);
    }

    private HealthUnitEntity getByIdOrThrow(UUID id) {
        return healthUnitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Health unit not found for id: " + id));
    }
}
