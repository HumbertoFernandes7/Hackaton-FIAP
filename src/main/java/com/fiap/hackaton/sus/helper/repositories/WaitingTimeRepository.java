package com.fiap.hackaton.sus.helper.repositories;

import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import com.fiap.hackaton.sus.helper.entities.WaitingTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WaitingTimeRepository extends JpaRepository<WaitingTimeEntity, Long> {

    boolean existsByHealthUnitId(HealthUnitEntity healthUnitEntity);

    Optional<WaitingTimeEntity> findByHealthUnitId(HealthUnitEntity healthUnitId);
}
