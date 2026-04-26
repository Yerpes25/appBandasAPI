package com.example.appBandas.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Asistencia;
import com.example.appBandas.servicios.AsistenciaServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST que gestiona las peticiones HTTP para la entidad Asistencia.
 * Recibe y envia los datos sobre si un usuario acudira o ha acudido a un evento,
 * gestionando las claves primarias compuestas.
 */
@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaControlador {

    private final AsistenciaServicio asistenciaServicio;

    public AsistenciaControlador(AsistenciaServicio asistenciaServicio) {
        this.asistenciaServicio = asistenciaServicio;
    }

    @GetMapping
    public List<Asistencia> obtenerTodas() {
        return asistenciaServicio.obtenerTodasLasAsistencias();
    }

    @GetMapping("/{idUsuario}/{idEvento}")
    public ResponseEntity<Asistencia> obtenerPorId(@PathVariable Integer idUsuario, @PathVariable Integer idEvento) {
        Optional<Asistencia> asistencia = asistenciaServicio.obtenerAsistencia(idUsuario, idEvento);
        if (asistencia.isPresent()) {
            return ResponseEntity.ok(asistencia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Asistencia> guardar(@RequestBody Asistencia asistencia) {
        Asistencia asistenciaGuardada = asistenciaServicio.guardarAsistencia(asistencia);
        return ResponseEntity.ok(asistenciaGuardada);
    }

    @DeleteMapping("/{idUsuario}/{idEvento}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idUsuario, @PathVariable Integer idEvento) {
        asistenciaServicio.eliminarAsistencia(idUsuario, idEvento);
        return ResponseEntity.ok().build();
    }
}