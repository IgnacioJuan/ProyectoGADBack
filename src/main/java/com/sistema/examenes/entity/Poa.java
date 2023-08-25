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
    @Column(name = "meta_alcanzar")
    private double meta_alcanzar;
    @Column(name = "meta_fisica")
    private double meta_fisica;
    @Column(name = "avance_real")
    private double avance_real;
    @Column(name = "fecha_inicio")
    private Date fecha_inicio;
    @Column(name = "fecha_fin")
    private Date fecha_fin;
    @Column(name = "localizacion")
    private String localizacion;
    @Column(name = "cobertura")
    private String cobertura;
    @Column(name = "barrio")
    private String barrio;
    @Column(name = "comunidad")
    private String comunidad;
    @Column(name = "nombre_funcionario")
    private String nombre_funcionario;
    @Column(name = "cargo")
    private String cargo;
    @Column(name = "recursos_propios")
    private double recursos_propios;
    @Column(name = "transferencias_gobierno")
    private double transferencias_gobierno;
    @Column(name = "convenios")
    private double convenios;
    @Column(name = "estado")
    private String estado;
    @Column(name = "linea_base")
    private String linea_base;
    @Column(name = "visible")
    private boolean visible;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poa")
    private Set<AprobacionPoa> lista_aprobaciones_poa = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poa")
    private Set<AprobacionActividades> lista_aprobaciones_actividades = new HashSet<>();
}
