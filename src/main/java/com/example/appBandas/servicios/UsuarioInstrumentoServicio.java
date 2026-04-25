package com.example.appBandas.servicios;

import com.example.appBandas.modelos.UsuarioInstrumento;
import com.example.appBandas.modelos.UsuarioInstrumentoId;
import com.example.appBandas.repositorios.UsuarioInstrumentoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para asignar a los usuarios el instrumento y la voz que tocan en la banda.
 */
@Service
public class UsuarioInstrumentoServicio {

    private final UsuarioInstrumentoRepository usuarioInstrumentoRepository;

    public UsuarioInstrumentoServicio(UsuarioInstrumentoRepository usuarioInstrumentoRepository) {
        this.usuarioInstrumentoRepository = usuarioInstrumentoRepository;
    }

    public List<UsuarioInstrumento> obtenerTodos() {
        return usuarioInstrumentoRepository.findAll();
    }

    public Optional<UsuarioInstrumento> obtenerPorId(Integer idUsuario, Integer idInstrumento, Integer idVoz) {
        UsuarioInstrumentoId id = new UsuarioInstrumentoId(idUsuario, idInstrumento, idVoz);
        return usuarioInstrumentoRepository.findById(id);
    }

    public UsuarioInstrumento guardar(UsuarioInstrumento usuarioInstrumento) {
        return usuarioInstrumentoRepository.save(usuarioInstrumento);
    }

    public void eliminar(Integer idUsuario, Integer idInstrumento, Integer idVoz) {
        UsuarioInstrumentoId id = new UsuarioInstrumentoId(idUsuario, idInstrumento, idVoz);
        usuarioInstrumentoRepository.deleteById(id);
    }
}