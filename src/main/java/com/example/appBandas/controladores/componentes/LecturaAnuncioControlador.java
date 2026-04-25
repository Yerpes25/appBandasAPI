package com.example.appBandas.controladores.componentes;

import com.example.appBandas.modelos.LecturaAnuncio;
import com.example.appBandas.servicios.LecturaAnuncioServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST de confirmaciones de lectura.
 */
@RestController
@RequestMapping("/api/lecturas-anuncios")
public class LecturaAnuncioControlador {

    private final LecturaAnuncioServicio lecturaAnuncioServicio;

    public LecturaAnuncioControlador(LecturaAnuncioServicio lecturaAnuncioServicio) {
        this.lecturaAnuncioServicio = lecturaAnuncioServicio;
    }

    @GetMapping
    public List<LecturaAnuncio> obtenerTodas() {
        return lecturaAnuncioServicio.obtenerTodas();
    }

    @GetMapping("/{idUsuario}/{idAnuncios}")
    public ResponseEntity<LecturaAnuncio> obtenerPorId(@PathVariable Integer idUsuario, @PathVariable Integer idAnuncios) {
        Optional<LecturaAnuncio> lectura = lecturaAnuncioServicio.obtenerPorId(idUsuario, idAnuncios);
        if (lectura.isPresent()) {
            return ResponseEntity.ok(lectura.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<LecturaAnuncio> guardar(@RequestBody LecturaAnuncio lectura) {
        return ResponseEntity.ok(lecturaAnuncioServicio.guardar(lectura));
    }

    @DeleteMapping("/{idUsuario}/{idAnuncios}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idUsuario, @PathVariable Integer idAnuncios) {
        lecturaAnuncioServicio.eliminar(idUsuario, idAnuncios);
        return ResponseEntity.ok().build();
    }
}