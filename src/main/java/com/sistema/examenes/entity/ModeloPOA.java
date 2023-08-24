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
@Table(name = "modelopoa")
public class ModeloPOA implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo_poa")
    private Long id_modelo_poa;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "fecha_inicial")
    private Date fecha_inicial;

    @Column(name = "fecha_final")
    private Date fecha_final;

    @Column(name = "visible")
    private boolean visible;

   @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_super_admin")
    private Usuario usuario;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "modelopoa")
    @JsonIgnore
    private Set<Proyecto> lista_proyectos = new HashSet<>();
}
