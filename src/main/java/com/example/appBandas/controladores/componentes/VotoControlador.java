package com.example.appBandas.controladores.componentes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Voto;
import com.example.appBandas.servicios.VotoServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para registrar las elecciones de los músicos en las votaciones.
 */
@RestController
@RequestMapping("/api/votos")
public class VotoControlador {

    private final VotoServicio votoServicio;

    public VotoControlador(VotoServicio votoServicio) {
        this.votoServicio = votoServicio;
    }

    @GetMapping
    public List<Voto> obtenerTodos() {
        return votoServicio.obtenerTodosLosVotos();
    }

    @GetMapping("/{idUsuario}/{idVotacion}")
    public ResponseEntity<Voto> obtenerPorId(@PathVariable Integer idUsuario, @PathVariable Integer idVotacion) {
        Optional<Voto> voto = votoServicio.obtenerVoto(idUsuario, idVotacion);
        if (voto.isPresent()) {
            return ResponseEntity.ok(voto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Voto> guardar(@RequestBody Voto voto) {
        return ResponseEntity.ok(votoServicio.guardarVoto(voto));
    }

    @DeleteMapping("/{idUsuario}/{idVotacion}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idUsuario, @PathVariable Integer idVotacion) {
        votoServicio.eliminarVoto(idUsuario, idVotacion);
        return ResponseEntity.ok().build();
    }
}