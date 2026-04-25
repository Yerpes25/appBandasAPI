package com.example.appBandas.controladores.directiva;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Liquidacion;
import com.example.appBandas.servicios.LiquidacionServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar los pagos o liquidaciones a los usuarios.
 */
@RestController
@RequestMapping("/api/liquidaciones")
public class LiquidacionControlador {

    private final LiquidacionServicio liquidacionServicio;

    public LiquidacionControlador(LiquidacionServicio liquidacionServicio) {
        this.liquidacionServicio = liquidacionServicio;
    }

    @GetMapping
    public List<Liquidacion> obtenerTodas() {
        return liquidacionServicio.obtenerTodasLasLiquidaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Liquidacion> obtenerPorId(@PathVariable Integer id) {
        Optional<Liquidacion> liquidacion = liquidacionServicio.obtenerLiquidacionPorId(id);
        if (liquidacion.isPresent()) {
            return ResponseEntity.ok(liquidacion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Liquidacion> guardar(@RequestBody Liquidacion liquidacion) {
        return ResponseEntity.ok(liquidacionServicio.guardarLiquidacion(liquidacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        liquidacionServicio.eliminarLiquidacion(id);
        return ResponseEntity.ok().build();
    }
}