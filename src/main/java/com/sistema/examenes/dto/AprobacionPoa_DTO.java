package com.sistema.examenes.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AprobacionPoa_DTO {
    private String observacion;
    private String estado;
    private Long id_aprobacionpoa;
    private String primer_nombre;
    private String primer_apellido;
    private Date fecha_aprobacion;

}
