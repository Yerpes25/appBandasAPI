package com.example.appBandas.controladores.directiva;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.appBandas.modelos.Insignia;
import com.example.appBandas.servicios.InsigniaServicio;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para las insignias y logros.
 * Envía la información de los méritos que pueden obtener los músicos.
 */
@RestController
@RequestMapping("/api/insignias")
public class InsigniaControlador {

    private final InsigniaServicio insigniaServicio;

    public InsigniaControlador(InsigniaServicio insigniaServicio) {
        this.insigniaServicio = insigniaServicio;
    }

    @GetMapping
    public List<Insignia> obtenerTodas() {
        return insigniaServicio.obtenerTodasLasInsignias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insignia> obtenerPorId(@PathVariable Integer id) {
        Optional<Insignia> insignia = insigniaServicio.obtenerInsigniaPorId(id);
        if (insignia.isPresent()) {
            return ResponseEntity.ok(insignia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Insignia> guardar(@RequestBody Insignia insignia) {
        return ResponseEntity.ok(insigniaServicio.guardarInsignia(insignia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        insigniaServicio.eliminarInsignia(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/asignar")
    public ResponseEntity<?> asignarInsignia(@RequestBody java.util.Map<String, Integer> datos) {
        try {
            Integer idUsuario = datos.get("idUsuario");
            Integer idInsignia = datos.get("idInsignia");
            
            // Lógica para guardar la relación (Puedes mover esto a tu InsigniaServicio)
            com.example.appBandas.modelos.UsuarioInsignia asignacion = new com.example.appBandas.modelos.UsuarioInsignia();
            asignacion.setId(new com.example.appBandas.modelos.UsuarioInsigniaId(idUsuario, idInsignia));
            asignacion.setfObtencion(java.time.LocalDate.now());
            
            // Requerirás inyectar UsuarioInsigniaRepository en tu servicio para hacer un .save()
            // usuarioInsigniaRepository.save(asignacion);
            
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al asignar: " + e.getMessage());
        }
    }
}