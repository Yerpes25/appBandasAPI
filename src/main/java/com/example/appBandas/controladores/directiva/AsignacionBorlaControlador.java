package com.example.appBandas.controladores.directiva;

import com.example.appBandas.modelos.AsignacionBorla;
import com.example.appBandas.servicios.AsignacionBorlaServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el escuadron de banderines y borlas.
 */
@RestController
@RequestMapping("/api/borlas")
public class AsignacionBorlaControlador {

    private final AsignacionBorlaServicio asignacionBorlaServicio;

    public AsignacionBorlaControlador(AsignacionBorlaServicio asignacionBorlaServicio) {
        this.asignacionBorlaServicio = asignacionBorlaServicio;
    }

    @GetMapping
    public List<AsignacionBorla> obtenerTodas() {
        return asignacionBorlaServicio.obtenerTodas();
    }

    @GetMapping("/{idAbanderado}/{idBorla}")
    public ResponseEntity<AsignacionBorla> obtenerPorId(@PathVariable Integer idAbanderado, @PathVariable Integer idBorla) {
        Optional<AsignacionBorla> asignacion = asignacionBorlaServicio.obtenerPorId(idAbanderado, idBorla);
        if (asignacion.isPresent()) {
            return ResponseEntity.ok(asignacion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AsignacionBorla> guardar(@RequestBody AsignacionBorla asignacion) {
        return ResponseEntity.ok(asignacionBorlaServicio.guardar(asignacion));
    }

    @DeleteMapping("/{idAbanderado}/{idBorla}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idAbanderado, @PathVariable Integer idBorla) {
        asignacionBorlaServicio.eliminar(idAbanderado, idBorla);
        return ResponseEntity.ok().build();
    }
}