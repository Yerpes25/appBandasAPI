package com.example.appBandas.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Votacion;
import com.example.appBandas.servicios.VotacionServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para listar las votaciones activas e inactivas.
 */
@RestController
@RequestMapping("/api/votaciones")
public class VotacionControlador {

    private final VotacionServicio votacionServicio;

    public VotacionControlador(VotacionServicio votacionServicio) {
        this.votacionServicio = votacionServicio;
    }

    @GetMapping
    public List<Votacion> obtenerTodas() {
        return votacionServicio.obtenerTodasLasVotaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Votacion> obtenerPorId(@PathVariable Integer id) {
        Optional<Votacion> votacion = votacionServicio.obtenerVotacionPorId(id);
        if (votacion.isPresent()) {
            return ResponseEntity.ok(votacion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Votacion> guardar(@RequestBody Votacion votacion) {
        return ResponseEntity.ok(votacionServicio.guardarVotacion(votacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        votacionServicio.eliminarVotacion(id);
        return ResponseEntity.ok().build();
    }
}