package com.example.appBandas.controladores.componentes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Partitura;
import com.example.appBandas.servicios.PartituraServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para acceder a los archivos digitales de las partituras.
 */
@RestController
@RequestMapping("/api/partituras")
public class PartituraControlador {

    private final PartituraServicio partituraServicio;

    public PartituraControlador(PartituraServicio partituraServicio) {
        this.partituraServicio = partituraServicio;
    }

    @GetMapping
    public List<Partitura> obtenerTodas() {
        return partituraServicio.obtenerTodasLasPartituras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partitura> obtenerPorId(@PathVariable Integer id) {
        Optional<Partitura> partitura = partituraServicio.obtenerPartituraPorId(id);
        if (partitura.isPresent()) {
            return ResponseEntity.ok(partitura.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Partitura> guardar(@RequestBody Partitura partitura) {
        return ResponseEntity.ok(partituraServicio.guardarPartitura(partitura));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        partituraServicio.eliminarPartitura(id);
        return ResponseEntity.ok().build();
    }
}