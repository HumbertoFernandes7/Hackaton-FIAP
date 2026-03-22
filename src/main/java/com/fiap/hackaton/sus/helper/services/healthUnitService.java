package com.fiap.hackaton.sus.helper.services;

import com.fiap.hackaton.sus.helper.entities.healthUnitEntity;
import com.fiap.hackaton.sus.helper.repositories.AddressEntityRepository;
import com.fiap.hackaton.sus.helper.repositories.healthUnitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class healthUnitService {

    private final healthUnitRepository healthUnitRepository;
    private final AddressEntityRepository addressEntityRepository;

    @Transactional
    public healthUnitEntity create(healthUnitEntity entity) {
        addressEntityRepository.save(entity.getAddressId());
        return healthUnitRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<healthUnitEntity> findAll() {
        return healthUnitRepository.findAll();
    }

    @Transactional(readOnly = true)
    public healthUnitEntity findById(UUID id) {
        return getByIdOrThrow(id);
    }

    @Transactional
    public healthUnitEntity update(healthUnitEntity entity) {
        getByIdOrThrow(entity.getId());
        addressEntityRepository.save(entity.getAddressId());
        return healthUnitRepository.save(entity);
    }

    @Transactional
    public void delete(UUID id) {
        healthUnitEntity entity = getByIdOrThrow(id);
        healthUnitRepository.delete(entity);
    }

    private healthUnitEntity getByIdOrThrow(UUID id) {
        return healthUnitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Health unit not found for id: " + id));
    }
}
