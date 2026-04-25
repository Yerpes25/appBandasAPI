package com.example.appBandas.controladores.componentes;

import com.example.appBandas.modelos.VehiculoCompartido;
import com.example.appBandas.servicios.VehiculoCompartidoServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestion de coches y plazas de viaje compartidas.
 */
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoCompartidoControlador {

    private final VehiculoCompartidoServicio vehiculoCompartidoServicio;

    public VehiculoCompartidoControlador(VehiculoCompartidoServicio vehiculoCompartidoServicio) {
        this.vehiculoCompartidoServicio = vehiculoCompartidoServicio;
    }

    @GetMapping
    public List<VehiculoCompartido> obtenerTodos() {
        return vehiculoCompartidoServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoCompartido> obtenerPorId(@PathVariable Integer id) {
        Optional<VehiculoCompartido> vehiculo = vehiculoCompartidoServicio.obtenerPorId(id);
        if (vehiculo.isPresent()) {
            return ResponseEntity.ok(vehiculo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<VehiculoCompartido> guardar(@RequestBody VehiculoCompartido vehiculo) {
        return ResponseEntity.ok(vehiculoCompartidoServicio.guardar(vehiculo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        vehiculoCompartidoServicio.eliminar(id);
        return ResponseEntity.ok().build();
    }
}