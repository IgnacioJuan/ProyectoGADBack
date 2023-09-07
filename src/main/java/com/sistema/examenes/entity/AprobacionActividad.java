package com.sistema.examenes.entity;

import com.sistema.examenes.entity.auth.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "aprobacion_actividad")
public class AprobacionActividad {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_aprobacionact")
    private Long id_aprobacionact;
    @Column(name = "observacion", length = 150)
    private String observacion;
    @Column(name = "estado", length = 50)
    private String estado;
    @Column(name = "visible")
    private boolean visible;

    @Column(name = "fecha_aprobacion")
    private String fecha_aprobacion;

    //Relaciones
    //ManyToOne POA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_poa")
    private Poa poa;

    //ManyToOne Actividades
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_actividad")
    private Actividades actividad;

    //ManyToOne Usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;



}
