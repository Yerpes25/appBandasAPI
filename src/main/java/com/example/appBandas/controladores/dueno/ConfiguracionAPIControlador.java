package com.example.appBandas.controladores.dueno;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.appBandas.modelos.Usuario;
import com.example.appBandas.servicios.UsuarioServicio;
import java.util.List;

/**
 * Controlador REST para gestionar la configuracion general de la banda.
 * Delega la logica de negocio y consultas al UsuarioServicio.
 */
@RestController
@RequestMapping("/api/configuracion")
public class ConfiguracionAPIControlador {

    private final UsuarioServicio usuarioServicio;

    // Inyectamos el servicio de usuarios en lugar del repositorio directo
    public ConfiguracionAPIControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    /**
     * Endpoint que devuelve todos los usuarios (dueños, administradores y músicos)
     * que pertenecen a una banda específica.
     */
    @GetMapping("/miembros/{idBanda}")
    public ResponseEntity<List<Usuario>> obtenerMiembrosBanda(@PathVariable Integer idBanda) {
        // Le pedimos los datos al servicio, manteniendo el controlador limpio
        List<Usuario> miembros = usuarioServicio.obtenerUsuariosPorBanda(idBanda);
        
        return ResponseEntity.ok(miembros);
    }
}