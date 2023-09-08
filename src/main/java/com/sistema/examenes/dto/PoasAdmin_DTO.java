package com.sistema.examenes.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PoasAdmin_DTO {
    private String nombre;
    private Long id_poa;
    private String barrio;
    private String cobertura;
    private String comunidad;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String estado;
    private String localizacion;

}
