package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema.examenes.entity.auth.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "modelopoa", indexes = {
        @Index(name = "idx_id_super_admin", columnList = "id_super_admin")
})
public class ModeloPOA implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo_poa")
    private Long id_modelo_poa;
    @Column(name = "nombre", length = 100)
    private String nombre;
    @Column(name = "descripcion", length = 2000)
    private String descripcion;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_inicial")
    private Date fecha_inicial;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_final")
    private Date fecha_final;
    @Column(name = "estado", length = 50)
    private String estado;
    @Column(name = "visible")
    private boolean visible;

    //Relaciones
    //ManyToOne
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_super_admin")
    @org.hibernate.annotations.ForeignKey(name = "FK_MODELOPOA_USUARIO")
    private Usuario usuario;

    //OneToMany Proyecto
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "modelopoa")
    @JsonIgnore
    private Set<Proyecto> lista_proyectos = new HashSet<>();
}
