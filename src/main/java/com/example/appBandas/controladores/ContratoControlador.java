package com.example.appBandas.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Contrato;
import com.example.appBandas.servicios.ContratoServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar los contratos.
 * Proporciona los puntos de acceso para la gestión económica y de actuaciones acordadas.
 */
@RestController
@RequestMapping("/api/contratos")
public class ContratoControlador {

    private final ContratoServicio contratoServicio;

    public ContratoControlador(ContratoServicio contratoServicio) {
        this.contratoServicio = contratoServicio;
    }

    @GetMapping
    public List<Contrato> obtenerTodos() {
        return contratoServicio.obtenerTodosLosContratos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> obtenerPorId(@PathVariable Integer id) {
        Optional<Contrato> contrato = contratoServicio.obtenerContratoPorId(id);
        if (contrato.isPresent()) {
            return ResponseEntity.ok(contrato.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Contrato> guardar(@RequestBody Contrato contrato) {
        return ResponseEntity.ok(contratoServicio.guardarContrato(contrato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        contratoServicio.eliminarContrato(id);
        return ResponseEntity.ok().build();
    }
}