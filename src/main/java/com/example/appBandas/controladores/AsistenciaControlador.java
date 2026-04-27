package com.example.appBandas.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.dtos.EstadisticasAsistenciaDTO;
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
    
    /*
     * Recibe la peticion de voto desde Android. 
     * Usamos @RequestParam para pasar los datos por la URL de forma sencilla.
     */
    @PostMapping("/votar")
    public ResponseEntity<?> registrarVoto(
            @RequestParam Integer idUsuario,
            @RequestParam Integer idEvento,
            @RequestParam String estado,
    		@RequestParam(required = false) String observacion){
        try {
            Asistencia asistencia = asistenciaServicio.registrarVoto(idUsuario, idEvento, estado, observacion);
            return ResponseEntity.ok(asistencia);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar voto: " + e.getMessage());
        }
    }
    
    @GetMapping("/usuario/{idUsuario}")
    public List<Asistencia> obtenerPorUsuario(@PathVariable Integer idUsuario) {
        return asistenciaServicio.obtenerAsistenciasPorUsuario(idUsuario);
    }
    
    @GetMapping("/estadisticas/{idUsuario}")
    public ResponseEntity<com.example.appBandas.dtos.EstadisticasAsistenciaDTO> obtenerEstadisticas(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(asistenciaServicio.obtenerEstadisticasUsuario(idUsuario));
    }
    
    @GetMapping("/estadisticas-conciertos/{idUsuario}")
    public ResponseEntity<EstadisticasAsistenciaDTO> obtenerEstadisticasConciertos(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(asistenciaServicio.obtenerEstadisticasOtrosEventos(idUsuario));
    }
}