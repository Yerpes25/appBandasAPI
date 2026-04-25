package com.example.appBandas.controladores.directiva;

import com.example.appBandas.modelos.InstrumentoVoz;
import com.example.appBandas.servicios.InstrumentoVozServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar las combinaciones posibles entre instrumentos y voces.
 * Permite definir, por ejemplo, que una "Trompeta" puede tocar la "1ª Voz" o "2ª Voz".
 */
@RestController
@RequestMapping("/api/instrumentos-voces")
public class InstrumentoVozControlador {

    private final InstrumentoVozServicio instrumentoVozServicio;

    public InstrumentoVozControlador(InstrumentoVozServicio instrumentoVozServicio) {
        this.instrumentoVozServicio = instrumentoVozServicio;
    }

    @GetMapping
    public List<InstrumentoVoz> obtenerTodas() {
        return instrumentoVozServicio.obtenerTodas();
    }

    @GetMapping("/{idInstrumento}/{idVoz}")
    public ResponseEntity<InstrumentoVoz> obtenerPorId(@PathVariable Integer idInstrumento, @PathVariable Integer idVoz) {
        Optional<InstrumentoVoz> combinacion = instrumentoVozServicio.obtenerPorId(idInstrumento, idVoz);
        if (combinacion.isPresent()) {
            return ResponseEntity.ok(combinacion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<InstrumentoVoz> guardar(@RequestBody InstrumentoVoz instrumentoVoz) {
        return ResponseEntity.ok(instrumentoVozServicio.guardar(instrumentoVoz));
    }

    @DeleteMapping("/{idInstrumento}/{idVoz}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idInstrumento, @PathVariable Integer idVoz) {
        instrumentoVozServicio.eliminar(idInstrumento, idVoz);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/instrumento/{idInstrumento}")
    public List<InstrumentoVoz> obtenerPorInstrumento(@PathVariable Integer idInstrumento) {
        return instrumentoVozServicio.obtenerPorInstrumento(idInstrumento);
    }
}