package com.sistema.examenes.dto;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Poa_DTO {
    private Long id_poa;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String localizacion;
    private String cobertura;
    private String barrio;
    private String comunidad;
    private String nombre_funcionario;
    private String cargo;
    private double recursos_propios;
    private double recursos_externos;
    private String linea_base;
}
