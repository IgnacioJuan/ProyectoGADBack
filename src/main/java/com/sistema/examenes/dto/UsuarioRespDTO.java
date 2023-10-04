package com.sistema.examenes.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioRespDTO {
    private Long id_programa;
    private Long id_usuario;
    private Long id_rol;
    private Long id_persona;
    private Long usuarioRolId;
    private String nombrePersona;
    private String apellidoPersona;
    private String nombreUsuario;
}
