package com.example.appBandas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appBandas.modelos.PasajeroVehiculo;
import com.example.appBandas.modelos.PasajeroVehiculoId;

/**
 * Repositorio de acceso a la tabla de Pasajeros_Vehiculo.
 */
@Repository
public interface PasajeroVehiculoRepository extends JpaRepository<PasajeroVehiculo, PasajeroVehiculoId> {
}