package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema.examenes.entity.auth.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "poa", indexes = {
        @Index(name = "idx_id_responsable_poa", columnList = "id_responsable"),
        @Index(name = "idx_id_proyecto", columnList = "id_proyecto")
})
public class Poa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_poa")
    private Long id_poa;
    @Column(name = "estado", length = 200)
    private String estado;
    @Column(name = "tipo_periodo")
    private String tipo_periodo;
    @Column(name = "barrio")
    private String barrio;
    @Column(name = "comunidad")
    private String comunidad;
    @Column(name = "localizacion")
    private String localizacion;
    @Column(name = "fecha_inicio")
    private Date fecha_inicio;
    @Column(name = "fecha_fin")
    private Date fecha_fin;
    @Column(name = "cobertura")
    private String cobertura;
    @Column(name = "linea_base")
    private double linea_base;
    @Column(name = "meta_planificada")
    private double meta_planificada;
    @Column(name = "meta_alcanzar")
    private double meta_alcanzar;
    @Column(name = "visible")
    private boolean visible;

    //fecha de creacion
    @Column(name = "fecha_creacion")
    private Date fecha_creacion;

    @PrePersist
    public void prePersist() {
        fecha_creacion = new Date();
    }
    
     @Transient
    public double getValorTotal() {
        return (double) meta_alcanzar / meta_planificada * 100;
    }


    //Relaciones
    //ManyToOne Usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_responsable")
    @org.hibernate.annotations.ForeignKey(name = "FK_POA_USUARIO")
    private Usuario usuario;

    //OneToMany AprobacionPOA
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poa")
    private Set<AprobacionPoa> lista_aprobaciones_poa = new HashSet<>();

    //OneToMany AprobacionActividad
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poa")
    private Set<AprobacionActividad> lista_aprobaciones_actividades = new HashSet<>();

    //ManyToOne proyecto
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_proyecto")
    @org.hibernate.annotations.ForeignKey(name = "FK_POA_PROYECTO")
    private Proyecto proyecto;

    //OneToMany Actividad
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poa")
    private Set<Actividades> lista_actividades = new HashSet<>();

}
