package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Insignia;
import com.example.appBandas.repositorios.InsigniaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar los logros e insignias que pueden conseguir los músicos.
 */
@Service
public class InsigniaServicio {

    private final InsigniaRepository insigniaRepository;

    public InsigniaServicio(InsigniaRepository insigniaRepository) {
        this.insigniaRepository = insigniaRepository;
    }

    public List<Insignia> obtenerTodasLasInsignias() {
        return insigniaRepository.findAll();
    }

    public Optional<Insignia> obtenerInsigniaPorId(Integer id) {
        return insigniaRepository.findById(id);
    }

    public Insignia guardarInsignia(Insignia insignia) {
        return insigniaRepository.save(insignia);
    }

    public void eliminarInsignia(Integer id) {
        insigniaRepository.deleteById(id);
    }
}