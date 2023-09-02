package com.sistema.examenes.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PoasAdmin_DTO {
    private Long id_poa;
    private String barrio;
    private String comunidad;
    private String cobertura;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String estado;
    private String tipo_periodo;
    private String localizacion;
    private double linea_base;
    private double meta_planificada;
    private double meta_alcanzar;

}
