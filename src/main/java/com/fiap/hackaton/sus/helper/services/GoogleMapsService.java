package com.fiap.hackaton.sus.helper.services;

import com.fiap.hackaton.sus.helper.dtos.DistanceTimeDTO;
import com.fiap.hackaton.sus.helper.exceptions.BusinessException;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


    public DistanceTimeDTO calculateDistanceAndTime(double latOrigem, double longOrigem, double latDestination, double longDestination) {

        try {
            LatLng origem = new LatLng(latOrigem, longOrigem);
            LatLng destination = new LatLng(latDestination, longDestination);

            DistanceMatrix matrix = DistanceMatrixApi.newRequest(maps)
                    .origins(origem)
                    .destinations(destination)
                    .mode(TravelMode.DRIVING)
                    .language("pt-BR")
                    .await();

            // O Google retorna uma matriz. Como mandamos 1 origem e 1 destino,
            // a resposta que queremos estará na linha 0, elemento 0.
            if (matrix != null && matrix.rows.length > 0 && matrix.rows[0].elements.length > 0) {
                DistanceMatrixElement element = matrix.rows[0].elements[0];

                if (element.status == DistanceMatrixElementStatus.OK) {
                    return new DistanceTimeDTO(
                            element.distance.humanReadable,
                            element.distance.inMeters,
                            element.duration.humanReadable,
                            element.duration.inSeconds
                    );
                } else {
                    throw new BusinessException("Não foi possível traçar uma rota entre as coordenadas. Status: " + element.status);
                }
            }

            throw new BusinessException("A API do Google Maps retornou uma matriz vazia.");

        } catch (Exception e) {
            throw new BusinessException("Erro ao se comunicar com API do Google Maps: " + e);
        }

    }

}
