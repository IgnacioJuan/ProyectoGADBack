package com.sistema.examenes.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PoaSolicitudPresupuesto_DTO {
    private String  nombre_proyecto;
    private Long id_poa;
    private String barrio;
    private String  cobertura;
    private String comunidad;
    private String  estado_poa;
    private double meta_alcanzar;
    private double meta_planificada;

}
