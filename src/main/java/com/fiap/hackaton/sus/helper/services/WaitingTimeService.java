package com.fiap.hackaton.sus.helper.services;

import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import com.fiap.hackaton.sus.helper.entities.WaitingTimeEntity;
import com.fiap.hackaton.sus.helper.exceptions.BadRequestBusinessException;
import com.fiap.hackaton.sus.helper.exceptions.NotFoundBusinessException;
import com.fiap.hackaton.sus.helper.repositories.HealthUnitRepository;
import com.fiap.hackaton.sus.helper.repositories.WaitingTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WaitingTimeService {

    private final HealthUnitRepository healthUnitRepository;
    private final WaitingTimeRepository waitingTimeRepository;

    @Transactional
    public WaitingTimeEntity create(WaitingTimeEntity entity) {
        if(waitingTimeRepository.existsByHealthUnitId(entity.getHealthUnitId())){
            throw new BadRequestBusinessException("Waiting time already exists for this health unit");
        }
        return waitingTimeRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<WaitingTimeEntity> findAll() {
        return waitingTimeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public WaitingTimeEntity findById(Long id) {
        return getByIdOrThrow(id);
    }

    @Transactional(readOnly = true)
    public WaitingTimeEntity findByHealthUnitId(HealthUnitEntity healthUnitId) {
        return waitingTimeRepository.findByHealthUnitId(healthUnitId)
                .orElseThrow(() -> new NotFoundBusinessException("Waiting time not found for this health unit"));
    }

    @Transactional(readOnly = true)
    public List<WaitingTimeEntity> findWaitingTimesByHealthUnitIds(List<UUID> healthUnitIds) {
        return waitingTimeRepository.findAllByHealthUnitId_IdIn(healthUnitIds);
    }

    @Transactional
    public WaitingTimeEntity update(WaitingTimeEntity entity) {
        return waitingTimeRepository.save(entity);
    }

    @Transactional
    public void delete(Long id) {
        WaitingTimeEntity entity = getByIdOrThrow(id);
        waitingTimeRepository.delete(entity);
    }

    private WaitingTimeEntity getByIdOrThrow(Long id) {
        return waitingTimeRepository.findById(id)
                .orElseThrow(() -> new NotFoundBusinessException("Waiting time not found for id: " + id));
    }
}