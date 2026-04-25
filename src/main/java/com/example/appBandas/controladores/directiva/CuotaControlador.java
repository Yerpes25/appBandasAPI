package com.example.appBandas.controladores.directiva;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Cuota;
import com.example.appBandas.servicios.CuotaServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar las cuotas de los músicos.
 * Permite a la app consultar los pagos pendientes o realizados.
 */
@RestController
@RequestMapping("/api/cuotas")
public class CuotaControlador {

    private final CuotaServicio cuotaServicio;

    public CuotaControlador(CuotaServicio cuotaServicio) {
        this.cuotaServicio = cuotaServicio;
    }

    @GetMapping
    public List<Cuota> obtenerTodas() {
        return cuotaServicio.obtenerTodasLasCuotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuota> obtenerPorId(@PathVariable Integer id) {
        Optional<Cuota> cuota = cuotaServicio.obtenerCuotaPorId(id);
        if (cuota.isPresent()) {
            return ResponseEntity.ok(cuota.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cuota> guardar(@RequestBody Cuota cuota) {
        return ResponseEntity.ok(cuotaServicio.guardarCuota(cuota));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        cuotaServicio.eliminarCuota(id);
        return ResponseEntity.ok().build();
    }
}