package com.sistema.examenes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PeriodoTotalPOA_DTO {
    private double total_presupuesto_referencial;
    private double total_recursos_propios;
    private double total_presupuesto_externo;
}
