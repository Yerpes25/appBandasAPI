package com.example.appBandas.servicios;

import com.example.appBandas.modelos.GaleriaMultimedia;
import com.example.appBandas.repositorios.GaleriaMultimediaRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que maneja la logica de negocio para la galeria multimedia.
 */
@Service
public class GaleriaMultimediaServicio {

    private final GaleriaMultimediaRepository galeriaMultimediaRepository;

    public GaleriaMultimediaServicio(GaleriaMultimediaRepository galeriaMultimediaRepository) {
        this.galeriaMultimediaRepository = galeriaMultimediaRepository;
    }

    public List<GaleriaMultimedia> obtenerTodas() {
        return galeriaMultimediaRepository.findAll();
    }

    public Optional<GaleriaMultimedia> obtenerPorId(Integer id) {
        return galeriaMultimediaRepository.findById(id);
    }

    public GaleriaMultimedia guardar(GaleriaMultimedia archivo) {
        return galeriaMultimediaRepository.save(archivo);
    }

    public void eliminar(Integer id) {
        galeriaMultimediaRepository.deleteById(id);
    }
}