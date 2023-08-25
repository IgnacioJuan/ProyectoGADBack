package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema.examenes.entity.auth.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "actividades")
public class Actividades {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Long id_actividad;
    @Column(name = "nombre", length = 200)
    private String nombre;
    @Column(name = "observaciones", length = 1000)
    private String observaciones;
    @Column(name = "presepuesto_referencial")
    private double presepuesto_referencial;
    @Column(name = "reforma_suplemento")
    private double reforma_suplemento;
    @Column(name = "reforma_traspaso_d")
    private double reforma_traspaso_d;
    @Column(name = "reforma_traspaso_i")
    private double reforma_traspaso_i;
    @Column(name = "presupuesto_externo")
    private double presupuesto_externo;
    @Column(name = "codificado")
    private double codificado;
    @Column(name = "ejecutado")
    private double ejecutado;
    @Column(name = "saldo")
    private double saldo;
    @Column(name = "visible")
    private boolean visible;

    //relacion de uno a muchos con la tabla reporte_actividad
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<ReporteActividad> lista_reporte_actividades = new HashSet<>();

    //relacion de uno a muchos con la tabla evidencia
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<Evidencias> lista_evidencias = new HashSet<>();

    //relacion de muchos a uno con la tabla detalle_trimestre
    @JsonIgnore
    @OneToMany(mappedBy = "actividad", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DetalleTrimestre> detalle_trimestres = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "actividad", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AprobacionEvidencia> lista_aprobacionesActividad  = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_poa")
    private Poa poa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_responsable")
    private Usuario usuario;
}
