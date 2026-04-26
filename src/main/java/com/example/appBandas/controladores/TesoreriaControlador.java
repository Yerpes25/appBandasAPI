package com.example.appBandas.controladores;

import com.example.appBandas.modelos.Transaccion;
import com.example.appBandas.servicios.TesoreriaServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador REST para gestionar las peticiones relacionadas con la tesoreria.
 * Delega la logica de calculo e insercion al servicio correspondiente.
 */
@RestController
@RequestMapping("/api/tesoreria")
public class TesoreriaControlador {

    private final TesoreriaServicio tesoreriaServicio;

    public TesoreriaControlador(TesoreriaServicio tesoreriaServicio) {
        this.tesoreriaServicio = tesoreriaServicio;
    }

    @GetMapping("/{idBanda}")
    public ResponseEntity<Map<String, Object>> obtenerResumenTesoreria(@PathVariable Integer idBanda) {
        return ResponseEntity.ok(tesoreriaServicio.obtenerResumenTesoreria(idBanda));
    }

    @PostMapping("/{idBanda}/nueva")
    public ResponseEntity<?> guardarNueva(@PathVariable Integer idBanda, @RequestBody Transaccion transaccion) {
        try {
            tesoreriaServicio.guardarNuevaTransaccion(idBanda, transaccion);
            return ResponseEntity.ok("Guardado correctamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}