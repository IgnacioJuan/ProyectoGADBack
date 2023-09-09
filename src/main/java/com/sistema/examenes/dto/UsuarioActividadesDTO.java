package com.sistema.examenes.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioActividadesDTO {
    private Long id_usuario;
    private String username;
    private String nombre;
    private String apellido;
    private String cargo;
    private Date fecha_asignacion;
}
