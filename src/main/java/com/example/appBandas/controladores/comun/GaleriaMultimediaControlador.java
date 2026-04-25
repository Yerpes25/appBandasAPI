package com.example.appBandas.controladores.comun;

import com.example.appBandas.modelos.GaleriaMultimedia;
import com.example.appBandas.servicios.GaleriaMultimediaServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para crear y consultar archivos multimedia de los eventos.
 */
@RestController
@RequestMapping("/api/galeria")
public class GaleriaMultimediaControlador {

    private final GaleriaMultimediaServicio galeriaMultimediaServicio;

    public GaleriaMultimediaControlador(GaleriaMultimediaServicio galeriaMultimediaServicio) {
        this.galeriaMultimediaServicio = galeriaMultimediaServicio;
    }

    @GetMapping
    public List<GaleriaMultimedia> obtenerTodas() {
        return galeriaMultimediaServicio.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GaleriaMultimedia> obtenerPorId(@PathVariable Integer id) {
        Optional<GaleriaMultimedia> archivo = galeriaMultimediaServicio.obtenerPorId(id);
        if (archivo.isPresent()) {
            return ResponseEntity.ok(archivo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<GaleriaMultimedia> guardar(@RequestBody GaleriaMultimedia archivo) {
        return ResponseEntity.ok(galeriaMultimediaServicio.guardar(archivo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        galeriaMultimediaServicio.eliminar(id);
        return ResponseEntity.ok().build();
    }
}