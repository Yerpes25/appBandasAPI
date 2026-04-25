package com.example.appBandas.controladores.directiva;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.RolCargo;
import com.example.appBandas.servicios.RolCargoServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para consultar los distintos cargos dentro de la banda.
 */
@RestController
@RequestMapping("/api/cargos")
public class RolCargoControlador {

    private final RolCargoServicio rolCargoServicio;

    public RolCargoControlador(RolCargoServicio rolCargoServicio) {
        this.rolCargoServicio = rolCargoServicio;
    }

    @GetMapping
    public List<RolCargo> obtenerTodos() {
        return rolCargoServicio.obtenerTodosLosCargos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolCargo> obtenerPorId(@PathVariable Integer id) {
        Optional<RolCargo> cargo = rolCargoServicio.obtenerCargoPorId(id);
        if (cargo.isPresent()) {
            return ResponseEntity.ok(cargo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RolCargo> guardar(@RequestBody RolCargo cargo) {
        return ResponseEntity.ok(rolCargoServicio.guardarCargo(cargo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        rolCargoServicio.eliminarCargo(id);
        return ResponseEntity.ok().build();
    }
}