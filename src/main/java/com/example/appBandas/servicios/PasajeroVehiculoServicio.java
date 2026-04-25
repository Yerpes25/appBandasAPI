package com.example.appBandas.servicios;

import com.example.appBandas.modelos.PasajeroVehiculo;
import com.example.appBandas.modelos.PasajeroVehiculoId;
import com.example.appBandas.repositorios.PasajeroVehiculoRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar las reservas de plazas en coches.
 */
@Service
public class PasajeroVehiculoServicio {

    private final PasajeroVehiculoRepository pasajeroVehiculoRepository;

    public PasajeroVehiculoServicio(PasajeroVehiculoRepository pasajeroVehiculoRepository) {
        this.pasajeroVehiculoRepository = pasajeroVehiculoRepository;
    }

    public List<PasajeroVehiculo> obtenerTodos() {
        return pasajeroVehiculoRepository.findAll();
    }

    public Optional<PasajeroVehiculo> obtenerPorId(Integer idUsuario, Integer idVehiculo) {
        PasajeroVehiculoId id = new PasajeroVehiculoId(idUsuario, idVehiculo);
        return pasajeroVehiculoRepository.findById(id);
    }

    public PasajeroVehiculo guardar(PasajeroVehiculo pasajeroVehiculo) {
        return pasajeroVehiculoRepository.save(pasajeroVehiculo);
    }

    public void eliminar(Integer idUsuario, Integer idVehiculo) {
        PasajeroVehiculoId id = new PasajeroVehiculoId(idUsuario, idVehiculo);
        pasajeroVehiculoRepository.deleteById(id);
    }
}