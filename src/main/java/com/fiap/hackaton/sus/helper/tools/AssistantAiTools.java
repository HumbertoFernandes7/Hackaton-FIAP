package com.fiap.hackaton.sus.helper.tools;


import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import com.fiap.hackaton.sus.helper.entities.WaitingTimeEntity;
import com.fiap.hackaton.sus.helper.services.GoogleMapsService;
import com.fiap.hackaton.sus.helper.services.HealthUnitService;
import com.fiap.hackaton.sus.helper.services.WaitingTimeService;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AssistantAiTools {

    private final HealthUnitService healthUnitService;
    private final WaitingTimeService waitingTimeService;
    private final GoogleMapsService googleMapsService;

    @Tool("Encontrar as unidades mais próximas")
    public List<HealthUnitEntity> getBestByLocation(String location){
        return healthUnitService.getBestByLocation(location);
    }

    @Tool("Encontrar o tempo de espera das unidades")
    public List<WaitingTimeEntity> getWaitingTimesByHealthUnitIds(List<UUID> healthUnitIds){
        return waitingTimeService.findWaitingTimesByHealthUnitIds(healthUnitIds);
    }

    @Tool("Encontrar o tempo de locomoção do usuário até a unidade")
    public String getTravelDuration(String origin, String destination, String healthUnitName){
        return googleMapsService.calculateTravelTime(origin, destination, healthUnitName);
    }
}