package com.example.appBandas.controladores.dueno;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.appBandas.modelos.Usuario;
import com.example.appBandas.repositorios.UsuarioRepository;
import java.util.List;

@RestController
@RequestMapping("/api/configuracion")
public class ConfiguracionAPIControlador {

    private final UsuarioRepository usuarioRepository;

    // Inyectamos el repositorio de usuarios directamente
    public ConfiguracionAPIControlador(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Endpoint que devuelve todos los usuarios (dueños, administradores y músicos)
     * que pertenecen a una banda específica.
     */
    @GetMapping("/miembros/{idBanda}")
    public ResponseEntity<List<Usuario>> obtenerMiembrosBanda(@PathVariable Integer idBanda) {
        // Utilizamos el método que ya tienes en tu UsuarioRepository
        List<Usuario> miembros = usuarioRepository.findByBanda_IdBanda(idBanda);
        
        return ResponseEntity.ok(miembros);
    }
}