package com.sistema.examenes.dto;

import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AprobPoa_DTO {
    private BigInteger id_poa;
    private String responsable;
    private String nombre_proyecto;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String nombre_ods;
    private String nombre_pnd;
    private String nombre_pdot;
    private String objetivo_proyecto;
    private String nombre_indicador;
    private String meta_proyecto;
    private Double linea_base;
    private String cobertura;
    private String localizacion;
    private String barrio;
    private String comunidad;
    private String tipo_periodo;
}
