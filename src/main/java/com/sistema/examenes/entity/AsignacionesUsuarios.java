package com.sistema.examenes.entity;

import com.sistema.examenes.entity.auth.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "asignaciones_usuarios")
public class AsignacionesUsuarios {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Long id_registro;
    @Column(name = "fecha_asignacion")
    private Date fecha_asignacion;
    @Column(name = "visible")
    private boolean visible;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usu_asignado")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_actividad")
    private Actividades actividad;
}
