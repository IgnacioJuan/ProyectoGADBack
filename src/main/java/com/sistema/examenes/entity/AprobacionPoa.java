package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema.examenes.entity.auth.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "aprobacion_poa")
public class AprobacionPoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aprobacionpoa")
    private Long id_aprobacionpoa;
    @Column(name = "observacion", length = 2000)
    private String observacion;
    @Column(name = "estado", length = 200)
    private String estado;
    @Column(name = "visible")
    private boolean visible;

    //Relaciones
    //ManyToOne POA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_poa")
    private Poa poa;

    //ManyToOne Proyecto
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_proyecto")
    private Proyecto proyecto;

    //ManyToOne Usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_usuario")
    private Usuario usuario;



}
