package com.example.appBandas.controladores.componentes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Prestamo;
import com.example.appBandas.servicios.PrestamoServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para llevar el control de los préstamos de material.
 */
@RestController
@RequestMapping("/api/prestamos")
public class PrestamoControlador {

    private final PrestamoServicio prestamoServicio;

    public PrestamoControlador(PrestamoServicio prestamoServicio) {
        this.prestamoServicio = prestamoServicio;
    }

    @GetMapping
    public List<Prestamo> obtenerTodos() {
        return prestamoServicio.obtenerTodosLosPrestamos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPorId(@PathVariable Integer id) {
        Optional<Prestamo> prestamo = prestamoServicio.obtenerPrestamoPorId(id);
        if (prestamo.isPresent()) {
            return ResponseEntity.ok(prestamo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Prestamo> guardar(@RequestBody Prestamo prestamo) {
        return ResponseEntity.ok(prestamoServicio.guardarPrestamo(prestamo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        prestamoServicio.eliminarPrestamo(id);
        return ResponseEntity.ok().build();
    }
}