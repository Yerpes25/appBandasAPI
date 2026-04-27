package com.example.appBandas.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Marcha;
import com.example.appBandas.servicios.MarchaServicio;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador REST para el repertorio musical.
 * Permite listar las marchas que toca la banda.
 */
@RestController
@RequestMapping("/api/marchas")
public class MarchaControlador {

    private final MarchaServicio marchaServicio;

    public MarchaControlador(MarchaServicio marchaServicio) {
        this.marchaServicio = marchaServicio;
    }

    @GetMapping
    public List<Marcha> obtenerTodas() {
        return marchaServicio.obtenerTodasLasMarchas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marcha> obtenerPorId(@PathVariable Integer id) {
        Optional<Marcha> marcha = marchaServicio.obtenerMarchaPorId(id);
        if (marcha.isPresent()) {
            return ResponseEntity.ok(marcha.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Marcha> guardar(@RequestBody Marcha marcha) {
        return ResponseEntity.ok(marchaServicio.guardarMarcha(marcha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        marchaServicio.eliminarMarcha(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/resumen/{idBanda}")
    public Map<String, Object> obtenerResumen(@PathVariable Integer idBanda) {
        return marchaServicio.obtenerResumenMarchas(idBanda);
    }
    
    @PostMapping("/{idBanda}/nueva")
    public ResponseEntity<?> guardarNuevaMarcha(@PathVariable Integer idBanda, @RequestBody Marcha marcha) {
        try {
            Marcha guardada = marchaServicio.guardarMarchaConBanda(idBanda, marcha);
            return ResponseEntity.ok(guardada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/banda/{idBanda}")
    public ResponseEntity<List<Marcha>> obtenerPorBanda(@PathVariable Integer idBanda) {
        return ResponseEntity.ok(marchaServicio.obtenerMarchasPorBanda(idBanda));
    }
}