package com.example.appBandas.servicios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Usuario;
import com.example.appBandas.repositorios.UsuarioRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar toda la lógica de negocio relacionada con los usuarios de la banda.
 * Aquí se realizan operaciones como buscar músicos, registrarlos, actualizarlos o darlos de baja,
 * conectando los controladores web con la base de datos a través del repositorio.
 */
@Service
public class UsuarioServicio {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServicio(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obtenerUsuarioPorDni(String dni) {
        return usuarioRepository.findByDni(dni);
    }

    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
    
    public long obtenerTodosLosUsuariosPorNumero() {
        return usuarioRepository.contarTodosLosUsuarios();
    }
}