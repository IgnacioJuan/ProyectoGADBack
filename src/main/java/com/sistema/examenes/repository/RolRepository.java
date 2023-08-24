package com.sistema.examenes.repository;

import com.sistema.examenes.entity.auth.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,Long> {
}
