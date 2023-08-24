package com.sistema.examenes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "evidencias")
public class Evidencias {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_evidencia")
    private Long id_evidencia;
    @Column(name = "nombre", length = 200)
    private String nombre;
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
    @Column(name = "ruta", length = 1000)
    private String ruta;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "visible")
    private boolean visible;

    //metodo para ingresar la fecha actual
    @PrePersist
    public void prePersist() {
        fecha = new Date();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_actividad")
    private Actividades actividad;

}
