package com.example.appBandas.controladores.directiva;

import com.example.appBandas.modelos.UsuarioInsignia;
import com.example.appBandas.servicios.UsuarioInsigniaServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para manejar el sistema de logros y motivacion.
 */
@RestController
@RequestMapping("/api/usuarios-insignias")
public class UsuarioInsigniaControlador {

    private final UsuarioInsigniaServicio usuarioInsigniaServicio;

    public UsuarioInsigniaControlador(UsuarioInsigniaServicio usuarioInsigniaServicio) {
        this.usuarioInsigniaServicio = usuarioInsigniaServicio;
    }

    @GetMapping
    public List<UsuarioInsignia> obtenerTodas() {
        return usuarioInsigniaServicio.obtenerTodas();
    }

    @GetMapping("/{idUsuario}/{idInsignia}")
    public ResponseEntity<UsuarioInsignia> obtenerPorId(@PathVariable Integer idUsuario, @PathVariable Integer idInsignia) {
        Optional<UsuarioInsignia> ui = usuarioInsigniaServicio.obtenerPorId(idUsuario, idInsignia);
        if (ui.isPresent()) {
            return ResponseEntity.ok(ui.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioInsignia> guardar(@RequestBody UsuarioInsignia ui) {
        return ResponseEntity.ok(usuarioInsigniaServicio.guardar(ui));
    }

    @DeleteMapping("/{idUsuario}/{idInsignia}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idUsuario, @PathVariable Integer idInsignia) {
        usuarioInsigniaServicio.eliminar(idUsuario, idInsignia);
        return ResponseEntity.ok().build();
    }
}