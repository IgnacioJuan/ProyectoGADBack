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
    private Long id_proyecto;
    private String nombreProyecto;
    private double meta_planificada;
    private String tipo_periodo;
    private Date fecha_inicio;
    private Date fecha_fin;
}