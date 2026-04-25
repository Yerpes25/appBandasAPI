package com.example.appBandas.servicios;

import com.example.appBandas.modelos.LecturaAnuncio;
import com.example.appBandas.modelos.LecturaAnuncioId;
import com.example.appBandas.repositorios.LecturaAnuncioRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para procesar los registros de los anuncios leidos.
 */
@Service
public class LecturaAnuncioServicio {

    private final LecturaAnuncioRepository lecturaAnuncioRepository;

    public LecturaAnuncioServicio(LecturaAnuncioRepository lecturaAnuncioRepository) {
        this.lecturaAnuncioRepository = lecturaAnuncioRepository;
    }

    public List<LecturaAnuncio> obtenerTodas() {
        return lecturaAnuncioRepository.findAll();
    }

    public Optional<LecturaAnuncio> obtenerPorId(Integer idUsuario, Integer idAnuncios) {
        LecturaAnuncioId id = new LecturaAnuncioId(idUsuario, idAnuncios);
        return lecturaAnuncioRepository.findById(id);
    }

    public LecturaAnuncio guardar(LecturaAnuncio lectura) {
        return lecturaAnuncioRepository.save(lectura);
    }

    public void eliminar(Integer idUsuario, Integer idAnuncios) {
        LecturaAnuncioId id = new LecturaAnuncioId(idUsuario, idAnuncios);
        lecturaAnuncioRepository.deleteById(id);
    }
}