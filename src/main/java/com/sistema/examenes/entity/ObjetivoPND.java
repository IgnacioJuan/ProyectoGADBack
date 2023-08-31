package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "objetivopnd")
public class ObjetivoPND implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_objetivo_pnd")
    private Long id_objetivo_pnd;
    @Column(name = "nombre", length = 200)
    private String nombre;
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
    @Column(name = "visible")
    private boolean visible;

    //Relaciones
    //ManyToOne con eje
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_eje")
    private Eje eje;

    //OneToMany Proyecto
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pnd")
    @JsonIgnore
    private Set<Proyecto> lista_proyectos = new HashSet<>();

}
