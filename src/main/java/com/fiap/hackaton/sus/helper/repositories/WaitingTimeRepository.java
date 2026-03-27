package com.fiap.hackaton.sus.helper.repositories;

import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import com.fiap.hackaton.sus.helper.entities.WaitingTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingTimeRepository extends JpaRepository<WaitingTimeEntity, Long> {

    boolean existsByHealthUnitId(HealthUnitEntity healthUnitEntity);

    WaitingTimeEntity findByHealthUnitId(HealthUnitEntity healthUnitId);
}
