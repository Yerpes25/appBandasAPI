package com.example.appBandas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Banda;
import com.example.appBandas.modelos.Evento;
import com.example.appBandas.servicios.EventoServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST que gestiona las peticiones HTTP para la entidad Evento.
 * Permite a la aplicacion movil acceder a la agenda de la banda, visualizando
 * ensayos, conciertos y actuaciones programadas.
 */
@RestController
@RequestMapping("/api/eventos")
public class EventoControlador {

    private final EventoServicio eventoServicio;
    
    @Autowired
    private BandaControlador bandaControlador;

    public EventoControlador(EventoServicio eventoServicio) {
        this.eventoServicio = eventoServicio;
    }

    @GetMapping
    public List<Evento> obtenerTodos() {
        return eventoServicio.obtenerTodosLosEventos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerPorId(@PathVariable Integer id) {
        Optional<Evento> evento = eventoServicio.obtenerEventoPorId(id);
        if (evento.isPresent()) {
            return ResponseEntity.ok(evento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Evento> guardar(@RequestBody Evento evento) {
        Evento eventoGuardado = eventoServicio.guardarEvento(evento);
        return ResponseEntity.ok(eventoGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        eventoServicio.eliminarEvento(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/total-ensayos-banda")
    public long ObtenerBanda() {
    	return eventoServicio.ensayosHoyPorBanda();
    }
    
    /**
     * Endpoint para obtener todos los eventos de la banda en el calendario.
     */
    @GetMapping("/banda/{idBanda}")
    public ResponseEntity<List<Evento>> obtenerPorBanda(@PathVariable Integer idBanda) {
        List<Evento> eventos = eventoServicio.obtenerEventosPorBanda(idBanda);
        return ResponseEntity.ok(eventos);
    }

    @PostMapping("/{idBanda}/nuevo")
    public ResponseEntity<?> crearEvento(@PathVariable Integer idBanda, @RequestBody java.util.Map<String, String> datos) {
        try {
            Evento guardado = eventoServicio.crearEventoParaBanda(idBanda, datos);
            return ResponseEntity.ok(guardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}