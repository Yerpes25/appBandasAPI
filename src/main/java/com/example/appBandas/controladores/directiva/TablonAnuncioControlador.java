package com.example.appBandas.controladores.directiva;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.TablonAnuncio;
import com.example.appBandas.servicios.TablonAnuncioServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el tablón de anuncios.
 * Envía a la aplicación móvil las notificaciones y avisos oficiales de la directiva.
 */
@RestController
@RequestMapping("/api/anuncios")
public class TablonAnuncioControlador {

    private final TablonAnuncioServicio tablonAnuncioServicio;

    public TablonAnuncioControlador(TablonAnuncioServicio tablonAnuncioServicio) {
        this.tablonAnuncioServicio = tablonAnuncioServicio;
    }

    @GetMapping
    public List<TablonAnuncio> obtenerTodos() {
        return tablonAnuncioServicio.obtenerTodosLosAnuncios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TablonAnuncio> obtenerPorId(@PathVariable Integer id) {
        Optional<TablonAnuncio> anuncio = tablonAnuncioServicio.obtenerAnuncioPorId(id);
        if (anuncio.isPresent()) {
            return ResponseEntity.ok(anuncio.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TablonAnuncio> guardar(@RequestBody TablonAnuncio anuncio) {
        TablonAnuncio anuncioGuardado = tablonAnuncioServicio.guardarAnuncio(anuncio);
        return ResponseEntity.ok(anuncioGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tablonAnuncioServicio.eliminarAnuncio(id);
        return ResponseEntity.ok().build();
    }
}