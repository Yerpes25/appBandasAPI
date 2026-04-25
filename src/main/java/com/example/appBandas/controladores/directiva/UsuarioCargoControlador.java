package com.example.appBandas.controladores.directiva;

import com.example.appBandas.modelos.UsuarioCargo;
import com.example.appBandas.servicios.UsuarioCargoServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el mantenimiento de la estructura organizativa.
 */
@RestController
@RequestMapping("/api/usuarios-cargos")
public class UsuarioCargoControlador {

    private final UsuarioCargoServicio usuarioCargoServicio;

    public UsuarioCargoControlador(UsuarioCargoServicio usuarioCargoServicio) {
        this.usuarioCargoServicio = usuarioCargoServicio;
    }

    @GetMapping
    public List<UsuarioCargo> obtenerTodos() {
        return usuarioCargoServicio.obtenerTodos();
    }

    @GetMapping("/{idUsuario}/{idCargo}")
    public ResponseEntity<UsuarioCargo> obtenerPorId(@PathVariable Integer idUsuario, @PathVariable Integer idCargo) {
        Optional<UsuarioCargo> uc = usuarioCargoServicio.obtenerPorId(idUsuario, idCargo);
        if (uc.isPresent()) {
            return ResponseEntity.ok(uc.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioCargo> guardar(@RequestBody UsuarioCargo uc) {
        return ResponseEntity.ok(usuarioCargoServicio.guardar(uc));
    }

    @DeleteMapping("/{idUsuario}/{idCargo}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idUsuario, @PathVariable Integer idCargo) {
        usuarioCargoServicio.eliminar(idUsuario, idCargo);
        return ResponseEntity.ok().build();
    }
}