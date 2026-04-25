package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.VehiculoCompartido;

/**
 * Repositorio para el acceso a datos de los vehiculos compartidos.
 */
@Repository
public interface VehiculoCompartidoRepository extends JpaRepository<VehiculoCompartido, Integer> {
}