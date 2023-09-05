package com.sistema.examenes.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SolicitudPoa {
    Long id_proyecto;
    Long meta_planificada;
    String cobertura;
    String barrio;
    String comunidad;
    String localizacion;
    String tipo_periodo;


}
