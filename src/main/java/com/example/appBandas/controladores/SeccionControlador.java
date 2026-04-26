package com.example.appBandas.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Seccion;
import com.example.appBandas.servicios.SeccionServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para clasificar a los instrumentos por familias o secciones.
 */
@RestController
@RequestMapping("/api/secciones")
public class SeccionControlador {

    private final SeccionServicio seccionServicio;

    public SeccionControlador(SeccionServicio seccionServicio) {
        this.seccionServicio = seccionServicio;
    }

    @GetMapping
    public List<Seccion> obtenerTodas() {
        return seccionServicio.obtenerTodasLasSecciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seccion> obtenerPorId(@PathVariable Integer id) {
        Optional<Seccion> seccion = seccionServicio.obtenerSeccionPorId(id);
        if (seccion.isPresent()) {
            return ResponseEntity.ok(seccion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Seccion> guardar(@RequestBody Seccion seccion) {
        return ResponseEntity.ok(seccionServicio.guardarSeccion(seccion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        seccionServicio.eliminarSeccion(id);
        return ResponseEntity.ok().build();
    }
}