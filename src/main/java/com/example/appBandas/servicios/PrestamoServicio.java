package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Prestamo;
import com.example.appBandas.repositorios.PrestamoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar las cesiones temporales de material (uniformes, instrumentos) a los músicos.
 */
@Service
public class PrestamoServicio {

    private final PrestamoRepository prestamoRepository;

    public PrestamoServicio(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public List<Prestamo> obtenerTodosLosPrestamos() {
        return prestamoRepository.findAll();
    }

    public Optional<Prestamo> obtenerPrestamoPorId(Integer id) {
        return prestamoRepository.findById(id);
    }

    public Prestamo guardarPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public void eliminarPrestamo(Integer id) {
        prestamoRepository.deleteById(id);
    }
}