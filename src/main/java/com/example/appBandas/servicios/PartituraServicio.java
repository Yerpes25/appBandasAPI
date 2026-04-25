package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Partitura;
import com.example.appBandas.repositorios.PartituraRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar los archivos digitales (PDF y audios) de las marchas.
 */
@Service
public class PartituraServicio {

    private final PartituraRepository partituraRepository;

    public PartituraServicio(PartituraRepository partituraRepository) {
        this.partituraRepository = partituraRepository;
    }

    public List<Partitura> obtenerTodasLasPartituras() {
        return partituraRepository.findAll();
    }

    public Optional<Partitura> obtenerPartituraPorId(Integer id) {
        return partituraRepository.findById(id);
    }

    public Partitura guardarPartitura(Partitura partitura) {
        return partituraRepository.save(partitura);
    }

    public void eliminarPartitura(Integer id) {
        partituraRepository.deleteById(id);
    }
}