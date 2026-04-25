package com.example.appBandas.servicios;

import com.example.appBandas.modelos.Mantenimiento;
import com.example.appBandas.repositorios.MantenimientoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio con la logica de negocio para la gestion de mantenimientos.
 */
@Service
public class MantenimientoServicio {

    private final MantenimientoRepository mantenimientoRepository;

    public MantenimientoServicio(MantenimientoRepository mantenimientoRepository) {
        this.mantenimientoRepository = mantenimientoRepository;
    }

    public List<Mantenimiento> obtenerTodos() {
        return mantenimientoRepository.findAll();
    }

    public Optional<Mantenimiento> obtenerPorId(Integer id) {
        return mantenimientoRepository.findById(id);
    }

    public Mantenimiento guardar(Mantenimiento mantenimiento) {
        return mantenimientoRepository.save(mantenimiento);
    }

    public void eliminar(Integer id) {
        mantenimientoRepository.deleteById(id);
    }
}