package com.sistema.examenes.entity;

import com.sistema.examenes.entity.auth.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "aprobacion_solicitud_presupuesto", indexes = {
        @Index(name = "idx_id_usuario_aprsol", columnList = "id_usuario"),
        @Index(name = "idx_id_solicitud", columnList = "id_solicitud")
})
public class Aprobacion_SolicitudPresupuesto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_aprobacionsolicitudpres")
    private Long id_aprobacionsolicitudpres;
    @Column(name = "observacion", length = 150)
    private String observacion;
    @Column(name = "estado", length = 50)
    private String estado;
    @Column(name = "visible")
    private boolean visible;

    @Column(name = "fecha_aprobacion")
    private Date fecha_aprobacion;

    //Relaciones

    //ManyToOne Solicitud
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud")
    @org.hibernate.annotations.ForeignKey(name = "FK_APRPRESUP_SOLICITUD")
    private SolicitudPresupuesto solicitud;

    //ManyToOne Usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    @org.hibernate.annotations.ForeignKey(name = "FK_APRPRESUP_USUARIO")
    private Usuario usuario;
}
