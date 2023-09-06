package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "archivo")
public class Archivo_s implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo")
    private Long id_archivo;
    @Column(name = "nombre", length = 10000)
    private String nombre;
    @Column(name = "descripcion", length = 10000)
    private String descripcion;
    @Column(name = "enlace")
    private String enlace;
    @Column(name = "fecha")
    private Date fecha = new Date();
    @Column(name = "valor")
    private double valor;
    @Column(name = "estado", length = 50)
    private String estado="PENDIENTE";
    @Column(name = "visible")
    private boolean visible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_actividad")
    private Actividades actividad;

    public Archivo_s() {
    }

    public Archivo_s( String nombre, String descripcion, String enlace,double valor,  boolean visible, Actividades actividad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.enlace = enlace;
        this.valor = valor;
        this.visible = visible;
        this.actividad = actividad;
    }

}

