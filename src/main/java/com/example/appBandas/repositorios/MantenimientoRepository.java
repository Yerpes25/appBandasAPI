package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.Mantenimiento;

/**
 * Repositorio de Spring Data JPA para acceder a la tabla de Mantenimiento.
 */
@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Integer> {
}