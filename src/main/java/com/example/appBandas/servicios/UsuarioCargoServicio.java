package com.example.appBandas.servicios;

import com.example.appBandas.modelos.UsuarioCargo;
import com.example.appBandas.modelos.UsuarioCargoId;
import com.example.appBandas.repositorios.UsuarioCargoRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar los nombramientos y ceses de cargos.
 */
@Service
public class UsuarioCargoServicio {

    private final UsuarioCargoRepository usuarioCargoRepository;

    public UsuarioCargoServicio(UsuarioCargoRepository usuarioCargoRepository) {
        this.usuarioCargoRepository = usuarioCargoRepository;
    }

    public List<UsuarioCargo> obtenerTodos() {
        return usuarioCargoRepository.findAll();
    }

    public Optional<UsuarioCargo> obtenerPorId(Integer idUsuario, Integer idCargo) {
        UsuarioCargoId id = new UsuarioCargoId(idUsuario, idCargo);
        return usuarioCargoRepository.findById(id);
    }

    public UsuarioCargo guardar(UsuarioCargo usuarioCargo) {
        return usuarioCargoRepository.save(usuarioCargo);
    }

    public void eliminar(Integer idUsuario, Integer idCargo) {
        UsuarioCargoId id = new UsuarioCargoId(idUsuario, idCargo);
        usuarioCargoRepository.deleteById(id);
    }
}