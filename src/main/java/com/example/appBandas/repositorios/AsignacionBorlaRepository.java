package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.AsignacionBorla;
import com.example.appBandas.modelos.AsignacionBorlaId;

/**
 * Repositorio para la tabla Asignacion_Borlas.
 */
@Repository
public interface AsignacionBorlaRepository extends JpaRepository<AsignacionBorla, AsignacionBorlaId> {
}