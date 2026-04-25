package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Cuota;
import com.example.appBandas.repositorios.CuotaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar los pagos mensuales de los miembros de la banda.
 */
@Service
public class CuotaServicio {

    private final CuotaRepository cuotaRepository;

    public CuotaServicio(CuotaRepository cuotaRepository) {
        this.cuotaRepository = cuotaRepository;
    }

    public List<Cuota> obtenerTodasLasCuotas() {
        return cuotaRepository.findAll();
    }

    public Optional<Cuota> obtenerCuotaPorId(Integer id) {
        return cuotaRepository.findById(id);
    }

    public Cuota guardarCuota(Cuota cuota) {
        return cuotaRepository.save(cuota);
    }

    public void eliminarCuota(Integer id) {
        cuotaRepository.deleteById(id);
    }
}