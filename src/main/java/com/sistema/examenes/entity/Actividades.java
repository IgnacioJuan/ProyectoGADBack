package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema.examenes.entity.auth.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "actividades", indexes = {
        @Index(name = "idx_id_responsable_act", columnList = "id_responsable"),
        @Index(name = "idx_id_poa_act", columnList = "id_poa")
})
public class Actividades {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Long id_actividad;
    @Column(name = "nombre", length = 200)
    private String nombre;
    @Column(name = "descripcion", length = 2000)
    private String descripcion;
    @Column(name = "codificado")
    private double codificado;
    @Column(name = "devengado")
    private double devengado;
    @Column(name = "recursos_propios")
    private double recursos_propios;
    @Column(name = "presupuesto_referencial")
    private double presupuesto_referencial;
    @Column(name = "estado", length = 200)
    private String estado;
    @Column(name = "visible")
    private boolean visible;

    //fecha inicio y fecha fin  de la actividad y creacion
    @Column(name = "fecha_inicio")
    private Date fecha_inicio;
    @Column(name = "fecha_fin")
    private Date fecha_fin;
    @Column(name = "fecha_creacion")
    private Date fecha_creacion;

    //Relaciones con tablas de presupuestoExterno, ReformaSuplemento, ReformaTraspasoIncremento, ReformaTraspasoDecremento
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<PresupuestoExterno> presupuestos = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<ReformaTraspaso_I> reformasI = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<ReformaTraspaso_D> reformasD = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<ReformaSuplemento> reformaSuplementos = new HashSet<>();

    //relacion de uno a muchos con la tabla reporte_actividad
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<ReporteActividad> lista_reporte_actividades = new HashSet<>();

    //relacion de uno a muchos con la tabla evidencia
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<Archivo_s> lista_archivo_s = new HashSet<>();


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<AprobacionActividad> aprobacionActividades = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_responsable")
    @org.hibernate.annotations.ForeignKey(name = "FK_ACTIVIDAD_USUARIO") // Nombre de la restricción
    private Usuario  usuario;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<Periodo> periodos = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "actividad")
    private Set<AsignacionesUsuarios> rausurios = new HashSet<>();

    //ManytoONe Poa

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_poa")
    @org.hibernate.annotations.ForeignKey(name = "FK_ACTIVIDAD_POA")
    private Poa poa;

}
  