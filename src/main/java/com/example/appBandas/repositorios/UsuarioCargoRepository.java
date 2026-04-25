package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.UsuarioCargo;
import com.example.appBandas.modelos.UsuarioCargoId;

/**
 * Repositorio para la tabla de asignaciones de la junta.
 */
@Repository
public interface UsuarioCargoRepository extends JpaRepository<UsuarioCargo, UsuarioCargoId> {
}