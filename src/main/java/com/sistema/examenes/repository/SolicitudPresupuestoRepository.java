package com.sistema.examenes.repository;

import com.sistema.examenes.entity.SolicitudPresupuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SolicitudPresupuestoRepository extends JpaRepository <SolicitudPresupuesto, Long> {
    @Query(value = "SELECT * from solicitud_presupuesto where visible =true ORDER BY fecha_solicitud desc", nativeQuery = true)
    List<SolicitudPresupuesto> listarSolicitudPresupuesto();

    @Query(value = "SELECT sp.id_solicitud_presupuesto, sp.estado, sp.fecha_solicitud, sp.monto_actual, sp.monto_total, sp.motivo, sp.reforma,\n" +
            "    u_responsable.username AS responsable_username, u_superadmin.username AS superadmin_username, a.nombre AS actividad,\n" +
            "    p.cargo AS cargo_responsable, p.primer_nombre AS primer_nombre_responsable, p.primer_apellido AS primer_apellido_responsable,\n" +
            "    pa.cargo AS cargo_superadmin, pa.primer_nombre AS primer_nombre_superadmin, pa.primer_apellido AS primer_apellido_superadmin\n" +
            "FROM solicitud_presupuesto AS sp\n" +
            "INNER JOIN usuarios AS u_responsable ON sp.id_responsable = u_responsable.id\n" +
            "INNER JOIN usuarios AS u_superadmin ON sp.id_superadmin = u_superadmin.id\n" +
            "INNER JOIN actividades AS a ON sp.id_actividad = a.id_actividad\n" +
            "INNER JOIN persona AS p ON u_responsable.persona_id_persona = p.id_persona\n" +
            "INNER JOIN persona AS pa ON u_superadmin.persona_id_persona = pa.id_persona\n" +
            "WHERE sp.visible = true AND u_responsable.visible = true  AND u_superadmin.visible = true AND a.visible = true  AND p.visible = true\n" +
            "AND pa.visible = true  AND sp.id_responsable = :idResponsable AND sp.estado = :estado", nativeQuery = true)
    List<Object[]> listarPoasPorResponsableEstado(Long idResponsable, String estado);


    @Query(value = "SELECT * FROM public.solicitud_presupuesto WHERE id_poa = :idPoa AND id_superadmin = :idSuperAdmin " +
            "AND estado = 'PENDIENTE' ORDER BY fecha_solicitud, id_responsable", nativeQuery = true)
    List<SolicitudPresupuesto> listarSolicitudPresupuestoAdminPoa(Long idSuperAdmin, Long idPoa);











}
