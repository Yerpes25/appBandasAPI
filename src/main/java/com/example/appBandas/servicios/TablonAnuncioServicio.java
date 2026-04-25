package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.TablonAnuncio;
import com.example.appBandas.repositorios.TablonAnuncioRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar las notificaciones y avisos oficiales para la banda.
 */
@Service
public class TablonAnuncioServicio {

    private final TablonAnuncioRepository tablonAnuncioRepository;

    public TablonAnuncioServicio(TablonAnuncioRepository tablonAnuncioRepository) {
        this.tablonAnuncioRepository = tablonAnuncioRepository;
    }

    public List<TablonAnuncio> obtenerTodosLosAnuncios() {
        return tablonAnuncioRepository.findAll();
    }

    public Optional<TablonAnuncio> obtenerAnuncioPorId(Integer id) {
        return tablonAnuncioRepository.findById(id);
    }

    public TablonAnuncio guardarAnuncio(TablonAnuncio anuncio) {
        return tablonAnuncioRepository.save(anuncio);
    }

    public void eliminarAnuncio(Integer id) {
        tablonAnuncioRepository.deleteById(id);
    }
}