package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Votacion;
import com.example.appBandas.repositorios.VotacionRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar las encuestas y procesos de votación.
 */
@Service
public class VotacionServicio {

    private final VotacionRepository votacionRepository;

    public VotacionServicio(VotacionRepository votacionRepository) {
        this.votacionRepository = votacionRepository;
    }

    public List<Votacion> obtenerTodasLasVotaciones() {
        return votacionRepository.findAll();
    }

    public Optional<Votacion> obtenerVotacionPorId(Integer id) {
        return votacionRepository.findById(id);
    }

    public Votacion guardarVotacion(Votacion votacion) {
        return votacionRepository.save(votacion);
    }

    public void eliminarVotacion(Integer id) {
        votacionRepository.deleteById(id);
    }
}