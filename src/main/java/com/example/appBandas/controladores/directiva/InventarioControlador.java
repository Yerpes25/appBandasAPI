package com.example.appBandas.controladores.directiva;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Inventario;
import com.example.appBandas.servicios.InventarioServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador REST para gestionar el inventario de la banda.
 * Permite a la app móvil consultar el stock de instrumentos y uniformes,
 * ideal para conectarlo con un lector de códigos QR en Android.
 */
@RestController
@RequestMapping("/api/inventario")
public class InventarioControlador {

    private final InventarioServicio inventarioServicio;

    public InventarioControlador(InventarioServicio inventarioServicio) {
        this.inventarioServicio = inventarioServicio;
    }

    @GetMapping
    public List<Inventario> obtenerTodos() {
        return inventarioServicio.obtenerTodoElInventario();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerPorId(@PathVariable Integer id) {
        Optional<Inventario> articulo = inventarioServicio.obtenerArticuloPorId(id);
        if (articulo.isPresent()) {
            return ResponseEntity.ok(articulo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Inventario> guardar(@RequestBody Inventario articulo) {
        Inventario articuloGuardado = inventarioServicio.guardarArticulo(articulo);
        return ResponseEntity.ok(articuloGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        inventarioServicio.eliminarArticulo(id);
        return ResponseEntity.ok().build();
    }
    
    /**
     * Devuelve el balance de inventario y la lista de artículos para el ID de banda dado.
     */
    @GetMapping("/resumen/{idBanda}")
    public Map<String, Object> obtenerResumen(@PathVariable Integer idBanda) {
        return inventarioServicio.obtenerResumenInventario(idBanda);
    }

    @PostMapping("/{idBanda}/nuevo")
    public ResponseEntity<?> guardarNuevoArticulo(@PathVariable Integer idBanda, @RequestBody Inventario articulo) {
        try {
            // El controlador solo delega la tarea al servicio
            Inventario guardado = inventarioServicio.guardarArticuloConBanda(idBanda, articulo);
            return ResponseEntity.ok(guardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}