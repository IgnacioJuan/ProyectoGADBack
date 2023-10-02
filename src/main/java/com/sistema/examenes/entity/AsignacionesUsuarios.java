package com.sistema.examenes.entity;

import com.sistema.examenes.entity.auth.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "asignaciones_usuarios", indexes = {
        @Index(name = "idx_id_actividad_asig", columnList = "id_actividad"),
        @Index(name = "idx_id_usu_asignado", columnList = "id_usu_asignado")
})
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
    @org.hibernate.annotations.ForeignKey(name = "FK_ASIGUSU_USUARIO")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_actividad")
    @org.hibernate.annotations.ForeignKey(name = "FK_ASIGUSU_ACTIVIDAD")
    private Actividades actividad;
}
