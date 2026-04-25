package com.example.appBandas.servicios;

import com.example.appBandas.modelos.AsignacionBorla;
import com.example.appBandas.modelos.AsignacionBorlaId;
import com.example.appBandas.repositorios.AsignacionBorlaRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de configuracion de la escolta del banderin.
 */
@Service
public class AsignacionBorlaServicio {

    private final AsignacionBorlaRepository asignacionBorlaRepository;

    public AsignacionBorlaServicio(AsignacionBorlaRepository asignacionBorlaRepository) {
        this.asignacionBorlaRepository = asignacionBorlaRepository;
    }

    public List<AsignacionBorla> obtenerTodas() {
        return asignacionBorlaRepository.findAll();
    }

    public Optional<AsignacionBorla> obtenerPorId(Integer idAbanderado, Integer idBorla) {
        AsignacionBorlaId id = new AsignacionBorlaId(idAbanderado, idBorla);
        return asignacionBorlaRepository.findById(id);
    }

    public AsignacionBorla guardar(AsignacionBorla asignacion) {
        return asignacionBorlaRepository.save(asignacion);
    }

    public void eliminar(Integer idAbanderado, Integer idBorla) {
        AsignacionBorlaId id = new AsignacionBorlaId(idAbanderado, idBorla);
        asignacionBorlaRepository.deleteById(id);
    }
}