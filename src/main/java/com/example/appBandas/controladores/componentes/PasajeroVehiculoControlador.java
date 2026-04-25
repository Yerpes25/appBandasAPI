package com.example.appBandas.controladores.componentes;

import com.example.appBandas.modelos.PasajeroVehiculo;
import com.example.appBandas.servicios.PasajeroVehiculoServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para subir y bajar pasajeros de un coche.
 */
@RestController
@RequestMapping("/api/pasajeros")
public class PasajeroVehiculoControlador {

    private final PasajeroVehiculoServicio pasajeroVehiculoServicio;

    public PasajeroVehiculoControlador(PasajeroVehiculoServicio pasajeroVehiculoServicio) {
        this.pasajeroVehiculoServicio = pasajeroVehiculoServicio;
    }

    @GetMapping
    public List<PasajeroVehiculo> obtenerTodos() {
        return pasajeroVehiculoServicio.obtenerTodos();
    }

    @GetMapping("/{idUsuario}/{idVehiculo}")
    public ResponseEntity<PasajeroVehiculo> obtenerPorId(@PathVariable Integer idUsuario, @PathVariable Integer idVehiculo) {
        Optional<PasajeroVehiculo> pasajero = pasajeroVehiculoServicio.obtenerPorId(idUsuario, idVehiculo);
        if (pasajero.isPresent()) {
            return ResponseEntity.ok(pasajero.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PasajeroVehiculo> guardar(@RequestBody PasajeroVehiculo pasajero) {
        return ResponseEntity.ok(pasajeroVehiculoServicio.guardar(pasajero));
    }

    @DeleteMapping("/{idUsuario}/{idVehiculo}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idUsuario, @PathVariable Integer idVehiculo) {
        pasajeroVehiculoServicio.eliminar(idUsuario, idVehiculo);
        return ResponseEntity.ok().build();
    }
}