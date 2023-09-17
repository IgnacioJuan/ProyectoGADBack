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
@Table(name = "poa")
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

    //fecha_inicio automatica
    @PrePersist
    public void prePersist() {
        fecha_creacion = new Date();
        //fecha inicio que sea 1 de enero del ano siguiente
        fecha_inicio = new Date();
        fecha_inicio.setYear(fecha_inicio.getYear() + 1);
        fecha_inicio.setMonth(0);
        fecha_inicio.setDate(1);
        fecha_inicio.setHours(0);
        fecha_inicio.setMinutes(0);
        fecha_inicio.setSeconds(0);
        //fecha fin que sea 31 de diciembre del ano actual
        fecha_fin = new Date();
        fecha_fin.setYear(fecha_fin.getYear() + 1);
        fecha_fin.setMonth(11);
        fecha_fin.setDate(31);
        fecha_fin.setHours(0);
        fecha_fin.setMinutes(0);
        fecha_fin.setSeconds(0);
    }
    //fecha_fin automatica con un ano de diferencia
    
     @Transient
    public double getValorTotal() {
        return (double) meta_alcanzar / meta_planificada * 100;
    }


    //Relaciones
    //ManyToOne Usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_responsable")
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
    private Proyecto proyecto;

    //OneToMany Actividad
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poa")
    private Set<Actividades> lista_actividades = new HashSet<>();

}
