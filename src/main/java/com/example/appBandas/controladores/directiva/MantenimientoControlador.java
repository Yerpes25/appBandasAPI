package com.example.appBandas.controladores.directiva;

import com.example.appBandas.modelos.Mantenimiento;
import com.example.appBandas.servicios.MantenimientoServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar peticiones HTTP de mantenimientos.
 */
@RestController
@RequestMapping("/api/mantenimientos")
public class MantenimientoControlador {

    private final MantenimientoServicio mantenimientoServicio;

    public MantenimientoControlador(MantenimientoServicio mantenimientoServicio) {
        this.mantenimientoServicio = mantenimientoServicio;
    }

    @GetMapping
    public List<Mantenimiento> obtenerTodos() {
        return mantenimientoServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mantenimiento> obtenerPorId(@PathVariable Integer id) {
        Optional<Mantenimiento> mantenimiento = mantenimientoServicio.obtenerPorId(id);
        if (mantenimiento.isPresent()) {
            return ResponseEntity.ok(mantenimiento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Mantenimiento> guardar(@RequestBody Mantenimiento mantenimiento) {
        return ResponseEntity.ok(mantenimientoServicio.guardar(mantenimiento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        mantenimientoServicio.eliminar(id);
        return ResponseEntity.ok().build();
    }
}