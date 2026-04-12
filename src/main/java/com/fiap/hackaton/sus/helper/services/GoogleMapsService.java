package com.fiap.hackaton.sus.helper.services;

import com.fiap.hackaton.sus.helper.dtos.DistanceTimeDTO;
import com.fiap.hackaton.sus.helper.exceptions.BusinessException;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class GoogleMapsService {

    private final GeoApiContext maps;

    public GeocodingResult[] getCoordinates(String address) {
        try {
            return GeocodingApi.geocode(maps, address).await();
        } catch (Exception e) {
            throw new BusinessException("Erro ao se comunicar com API do Google Maps: " + e);
        }
    }

    public String calculateTravelTime(String origin, String destination, String healthUnitName) {
        if (origin == null || origin.isBlank()) {
            throw new BusinessException("A origem deve ser informada para calcular o tempo de deslocamento.");
        }

        if (destination == null || destination.isBlank()) {
            throw new BusinessException("O destino deve ser informado para calcular o tempo de deslocamento.");
        }

        try {
            DistanceMatrix matrix = DistanceMatrixApi.newRequest(maps)
                    .origins(origin.trim())
                    .destinations(destination.trim())
                    .mode(TravelMode.DRIVING)
                    .language("pt-BR")
                    .departureTime(Instant.now())
                    .await();

            if (matrix != null && matrix.rows.length > 0 && matrix.rows[0].elements.length > 0) {
                DistanceMatrixElement element = matrix.rows[0].elements[0];

                if (element.status == DistanceMatrixElementStatus.OK && element.duration != null) {
                    System.out.println("Unidade: " + healthUnitName +  " Destino : " + destination + " Tempo de locomoção: " + element.duration.humanReadable);
                    return "Unidade: " + healthUnitName +  " Destino : " + destination + " Tempo de locomoção: " + element.duration.humanReadable;
                }

                throw new BusinessException("Não foi possível calcular o tempo de deslocamento. Status: " + element.status);
            }

            throw new BusinessException("A API do Google Maps retornou uma matriz vazia.");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("Erro ao se comunicar com API do Google Maps: " + e.getMessage());
        }
    }
}
