package com.sistema.examenes.dto;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AprobPoa_DTO {
    private Long id_poa;
    private String direccion_departamental;
    private String responsable;
    private String correo_responsable;
    private String area;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String codigo;
    private String nombre_proyecto;
    private String descripcion_proyecto;
    private String nombre_componente;
    private String nombre_ods;
    private String nombre_pnd;
    private String nombre_pdot;
    private String objetivo_proyecto;
    private String nombre_indicador;
    private String nombre_metapdot;
    private String meta_proyecto;
    private String nombre_completo;
    //private String meta_planificada;
    private Double linea_base;
    private String cobertura;
    private String localizacion;
    private String barrio;
    private String comunidad;
    private String tipo_periodo;
    private String cargo; 
    //private Date fecha_emision;    
}
