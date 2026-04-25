package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.RolCargo;

/**
 * Repositorio para gestionar las operaciones de base de datos de la entidad
 * RolCargo.
 */
@Repository
public interface RolCargoRepository extends JpaRepository<RolCargo, Integer> {
}