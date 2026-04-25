package com.example.appBandas.servicios;

import com.example.appBandas.modelos.Liquidacion;
import com.example.appBandas.repositorios.LiquidacionRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar las liquidaciones y pagos a los usuarios tras los contratos.
 */
@Service
public class LiquidacionServicio {

    private final LiquidacionRepository liquidacionRepository;

    public LiquidacionServicio(LiquidacionRepository liquidacionRepository) {
        this.liquidacionRepository = liquidacionRepository;
    }

    public List<Liquidacion> obtenerTodasLasLiquidaciones() {
        return liquidacionRepository.findAll();
    }

    public Optional<Liquidacion> obtenerLiquidacionPorId(Integer id) {
        return liquidacionRepository.findById(id);
    }

    public Liquidacion guardarLiquidacion(Liquidacion liquidacion) {
        return liquidacionRepository.save(liquidacion);
    }

    public void eliminarLiquidacion(Integer id) {
        liquidacionRepository.deleteById(id);
    }
}