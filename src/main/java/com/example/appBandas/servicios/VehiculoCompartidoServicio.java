package com.example.appBandas.servicios;

import com.example.appBandas.modelos.VehiculoCompartido;
import com.example.appBandas.repositorios.VehiculoCompartidoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que maneja la logica de negocio para los vehiculos compartidos.
 */
@Service
public class VehiculoCompartidoServicio {

    private final VehiculoCompartidoRepository vehiculoCompartidoRepository;

    public VehiculoCompartidoServicio(VehiculoCompartidoRepository vehiculoCompartidoRepository) {
        this.vehiculoCompartidoRepository = vehiculoCompartidoRepository;
    }

    public List<VehiculoCompartido> obtenerTodos() {
        return vehiculoCompartidoRepository.findAll();
    }

    public Optional<VehiculoCompartido> obtenerPorId(Integer id) {
        return vehiculoCompartidoRepository.findById(id);
    }

    public VehiculoCompartido guardar(VehiculoCompartido vehiculo) {
        return vehiculoCompartidoRepository.save(vehiculo);
    }

    public void eliminar(Integer id) {
        vehiculoCompartidoRepository.deleteById(id);
    }
}