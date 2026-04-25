package com.example.appBandas.servicios;

import com.example.appBandas.modelos.InstrumentoVoz;
import com.example.appBandas.modelos.InstrumentoVozId;
import com.example.appBandas.repositorios.InstrumentoVozRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar las combinaciones posibles entre instrumentos y voces.
 */
@Service
public class InstrumentoVozServicio {

    private final InstrumentoVozRepository instrumentoVozRepository;

    public InstrumentoVozServicio(InstrumentoVozRepository instrumentoVozRepository) {
        this.instrumentoVozRepository = instrumentoVozRepository;
    }

    public List<InstrumentoVoz> obtenerTodas() {
        return instrumentoVozRepository.findAll();
    }

    public Optional<InstrumentoVoz> obtenerPorId(Integer idInstrumento, Integer idVoz) {
        InstrumentoVozId id = new InstrumentoVozId(idInstrumento, idVoz);
        return instrumentoVozRepository.findById(id);
    }

    public InstrumentoVoz guardar(InstrumentoVoz instrumentoVoz) {
        return instrumentoVozRepository.save(instrumentoVoz);
    }

    public void eliminar(Integer idInstrumento, Integer idVoz) {
        InstrumentoVozId id = new InstrumentoVozId(idInstrumento, idVoz);
        instrumentoVozRepository.deleteById(id);
    }
    
    public List<InstrumentoVoz> obtenerPorInstrumento(Integer id) {
        return instrumentoVozRepository.findByInstrumento_IdInstrumento(id);
    }
}