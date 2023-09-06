package com.sistema.examenes.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AprobacionEvidenciaDTO {
    private String observacion;
    private String estado;
    private Long id_aprobacionevid;
    private String primer_nombre;
    private String primer_apellido;

}
