package com.fiap.hackaton.sus.helper.repositories;

import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;
import java.util.UUID;

public interface HealthUnitRepository extends JpaRepository<HealthUnitEntity, UUID> {


    @NativeQuery("""
            SELECT * FROM tb_health_unit
            WHERE (6371 * acos(cos(radians(?1)) * cos(radians(latitude)) * cos(radians(longitude) - radians(?2)) + 
            sin(radians(?1)) * sin(radians(latitude)))) <= ?3 
            AND is_active = 1 
            ORDER BY (6371 * acos(cos(radians(?1)) * cos(radians(latitude)) * cos(radians(longitude) - radians(?2)) + 
            sin(radians(?1)) * sin(radians(latitude)))) ASC
            """)
    List<HealthUnitEntity> findNearbyUnits(double userLat, double userLon, double maxKmRange);

}
