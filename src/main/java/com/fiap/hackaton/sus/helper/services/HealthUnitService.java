package com.fiap.hackaton.sus.helper.services;

import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import com.fiap.hackaton.sus.helper.exceptions.BadRequestBusinessException;
import com.fiap.hackaton.sus.helper.exceptions.NotFoundBusinessException;
import com.fiap.hackaton.sus.helper.repositories.AddressEntityRepository;
import com.fiap.hackaton.sus.helper.repositories.HealthUnitRepository;
import com.google.maps.model.GeocodingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HealthUnitService {

    private static final double MAX_KM_RANGE = 10.0;

    private final HealthUnitRepository healthUnitRepository;
    private final AddressEntityRepository addressEntityRepository;
    private final GoogleMapsService googleMapsService;

    @Transactional
    public HealthUnitEntity create(HealthUnitEntity entity) {
        validateOperatingHours(entity);
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
        validateOperatingHours(entity);
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
                .orElseThrow(() -> new NotFoundBusinessException("Health unit not found for id: " + id));
    }

    private void validateOperatingHours(HealthUnitEntity entity) {
        if (!entity.isOpen24h() && (entity.getOpenTime() == null || entity.getCloseTime() == null)) {
            throw new BadRequestBusinessException("Open time and close time are required when open24h is false");
        }
    }

    public List<HealthUnitEntity> getBestByLocation(String location) {

        GeocodingResult[] coordinates = googleMapsService.getCoordinates(location);

        if (coordinates == null || coordinates.length == 0) {
            throw new BadRequestBusinessException("Não foi possível localizar o endereço informado");
        }

        double userLat = coordinates[0].geometry.location.lat;
        double userLon = coordinates[0].geometry.location.lng;

        return healthUnitRepository.findNearbyUnits(userLat, userLon, MAX_KM_RANGE);
    }
}
