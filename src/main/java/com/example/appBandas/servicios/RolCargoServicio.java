package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.RolCargo;
import com.example.appBandas.repositorios.RolCargoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar los cargos y roles dentro de la junta o área musical.
 */
@Service
public class RolCargoServicio {

    private final RolCargoRepository rolCargoRepository;

    public RolCargoServicio(RolCargoRepository rolCargoRepository) {
        this.rolCargoRepository = rolCargoRepository;
    }

    public List<RolCargo> obtenerTodosLosCargos() {
        return rolCargoRepository.findAll();
    }

    public Optional<RolCargo> obtenerCargoPorId(Integer id) {
        return rolCargoRepository.findById(id);
    }

    public RolCargo guardarCargo(RolCargo rolCargo) {
        return rolCargoRepository.save(rolCargo);
    }

    public void eliminarCargo(Integer id) {
        rolCargoRepository.deleteById(id);
    }
}