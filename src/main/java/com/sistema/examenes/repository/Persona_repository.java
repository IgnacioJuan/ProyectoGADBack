package com.sistema.examenes.repository;


import com.sistema.examenes.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Persona_repository extends JpaRepository<Persona, Long> {
    @Query(value = "SELECT * FROM persona p JOIN usuarios u ON p.id_persona = u.persona_id_persona WHERE u.username = :username", nativeQuery = true)
    public Persona obtenerPersona(String username);
    
    @Query(value = "SELECT * FROM persona p JOIN usuarios u ON p.id_persona = u.persona_id_persona WHERE u.id = :id", nativeQuery = true)
    public Persona obtenerPersonaUsuario(Long id);

    public Persona findByCedula(String cedula);
    @Query(value = "select per.*  from persona per join usuarios u on u.persona_id_persona = per.id_persona\n" +
            "join actividades ac on ac.id_responsable=u.id join archivo ar on ar.id_actividad=ac.id_actividad\n" +
            "where ac.id_actividad=:id", nativeQuery = true)
    public Persona obtenercorreo(Long id);



}
