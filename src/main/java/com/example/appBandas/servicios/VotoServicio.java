package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Voto;
import com.example.appBandas.modelos.VotoId;
import com.example.appBandas.repositorios.VotoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para registrar las respuestas de los usuarios en una votación.
 */
@Service
public class VotoServicio {

    private final VotoRepository votoRepository;

    public VotoServicio(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public List<Voto> obtenerTodosLosVotos() {
        return votoRepository.findAll();
    }

    public Optional<Voto> obtenerVoto(Integer idUsuario, Integer idVotacion) {
        VotoId id = new VotoId(idUsuario, idVotacion);
        return votoRepository.findById(id);
    }

    public Voto guardarVoto(Voto voto) {
        return votoRepository.save(voto);
    }

    public void eliminarVoto(Integer idUsuario, Integer idVotacion) {
        VotoId id = new VotoId(idUsuario, idVotacion);
        votoRepository.deleteById(id);
    }
}