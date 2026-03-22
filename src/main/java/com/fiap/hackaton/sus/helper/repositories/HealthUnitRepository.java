package com.fiap.hackaton.sus.helper.repositories;

import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HealthUnitRepository extends JpaRepository<HealthUnitEntity, UUID> {
}
