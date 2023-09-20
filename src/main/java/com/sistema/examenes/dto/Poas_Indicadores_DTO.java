package com.sistema.examenes.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Poas_Indicadores_DTO {
    private String  nombre_proyecto;
    private Long id_poa;
    private String localizacion;
    private String tipo_periodo;
    private double linea_base;
    private double meta_alcanzar;
    private double meta_planificada;
    private String  tipo_evaluacion;
    private String  nombre_metapdot;
    private BigDecimal porcentaje_cumplimiento;





}
