package com.fiap.hackaton.sus.helper.tools;


import com.fiap.hackaton.sus.helper.entities.HealthUnitEntity;
import com.fiap.hackaton.sus.helper.services.HealthUnitService;
import com.fiap.hackaton.sus.helper.services.WaitingTimeService;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AssistantAiTools {

    private final HealthUnitService healthUnitService;
    private final WaitingTimeService waitingTimeService;

    @Tool("Encontra a as unidades mais próximas")
    public List<HealthUnitEntity> getBestByLocation(String location){
        return healthUnitService.getBestByLocation(location);
    }

    /*@Tool("Retorna o tempo de espera")
    public List<WaitingTimeEntity> getWaitingTime(List<HealthUnitEntity> healthUnits){
        return null;
    }*/
}