package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Voz;
import com.example.appBandas.repositorios.VozRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar las voces de las partituras (1º Alta, 1º Fuerte, Única, etc.).
 */
@Service
public class VozServicio {

    private final VozRepository vozRepository;

    public VozServicio(VozRepository vozRepository) {
        this.vozRepository = vozRepository;
    }

    public List<Voz> obtenerTodasLasVoces() {
        return vozRepository.findAll();
    }

    public Optional<Voz> obtenerVozPorId(Integer id) {
        return vozRepository.findById(id);
    }

    public Voz guardarVoz(Voz voz) {
        return vozRepository.save(voz);
    }

    public void eliminarVoz(Integer id) {
        vozRepository.deleteById(id);
    }
}