package com.fiap.hackaton.sus.helper.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistanceTimeDTO {

    private String distanciaTexto; // Ex: "5.4 km"
    private long distanciaMetros;  // Ex: 5400
    private String tempoTexto;     // Ex: "15 min"
    private long tempoSegundos;

}
