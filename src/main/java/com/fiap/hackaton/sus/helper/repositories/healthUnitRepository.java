package com.fiap.hackaton.sus.helper.repositories;

import com.fiap.hackaton.sus.helper.entities.healthUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface healthUnitRepository extends JpaRepository<healthUnitEntity, UUID> {
}
