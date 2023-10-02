package com.sistema.examenes.entity;

import com.sistema.examenes.entity.auth.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "aprobacion_actividad", indexes = {
        @Index(name = "idx_id_usuario_apract", columnList = "id_usuario"),
        @Index(name = "idx_id_actividad_apract", columnList = "id_actividad"),
        @Index(name = "idx_id_poa_apra", columnList = "id_poa")
})
public class AprobacionActividad {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_aprobacionact")
    private Long id_aprobacionact;
    @Column(name = "observacion", length = 150)
    private String observacion;
    @Column(name = "estado", length = 50)
    private String estado;
    @Column(name = "visible")
    private boolean visible;

    @Column(name = "fecha_aprobacion")
    private Date fechaAprobacion;


    // Otros atributos y métodos de la entidad



    //Relaciones
    //ManyToOne POA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_poa")
    @org.hibernate.annotations.ForeignKey(name = "FK_APRACTIV_POA")
    private Poa poa;

    //ManyToOne Actividades
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_actividad")
    @org.hibernate.annotations.ForeignKey(name = "FK_APRACTIV_ACTIVIDAD")
    private Actividades actividad;

    //ManyToOne Usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    @org.hibernate.annotations.ForeignKey(name = "FK_APRACTIV_USUARIO")
    private Usuario usuario;

    @PrePersist
    protected void onCreate() {
        fechaAprobacion = new Date(); // Configura la fecha actual al crear la entidad
    }

}
