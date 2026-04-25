package com.example.appBandas.controladores.comun;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Voz;
import com.example.appBandas.servicios.VozServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar las voces de las partituras.
 */
@RestController
@RequestMapping("/api/voces")
public class VozControlador {

    private final VozServicio vozServicio;

    public VozControlador(VozServicio vozServicio) {
        this.vozServicio = vozServicio;
    }

    @GetMapping
    public List<Voz> obtenerTodas() {
        return vozServicio.obtenerTodasLasVoces();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voz> obtenerPorId(@PathVariable Integer id) {
        Optional<Voz> voz = vozServicio.obtenerVozPorId(id);
        if (voz.isPresent()) {
            return ResponseEntity.ok(voz.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Voz> guardar(@RequestBody Voz voz) {
        return ResponseEntity.ok(vozServicio.guardarVoz(voz));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        vozServicio.eliminarVoz(id);
        return ResponseEntity.ok().build();
    }
}