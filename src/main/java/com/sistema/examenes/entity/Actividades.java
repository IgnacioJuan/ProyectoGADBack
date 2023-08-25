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
    @Column(name = "presupuesto_referencial")
    private double presupuesto_referencial;
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

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<ReformaTraspaso_I> reformasI = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<ReformaTraspaso_D> reformasD = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<PresupuestoExterno> presupuestos = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<ReformaSuplemento> reformaSuplementos = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<AprobacionActividades> aprobacionActividades = new HashSet<>();



}
