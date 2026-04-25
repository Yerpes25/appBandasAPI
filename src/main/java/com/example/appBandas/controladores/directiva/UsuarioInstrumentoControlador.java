package com.example.appBandas.controladores.directiva;

import com.example.appBandas.modelos.UsuarioInstrumento;
import com.example.appBandas.servicios.UsuarioInstrumentoServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para asignar a los músicos qué instrumento y voz tocan en la banda.
 * También permite definir atributos extras como si el músico es solista.
 */
@RestController
@RequestMapping("/api/usuarios-instrumentos")
public class UsuarioInstrumentoControlador {

    private final UsuarioInstrumentoServicio usuarioInstrumentoServicio;

    public UsuarioInstrumentoControlador(UsuarioInstrumentoServicio usuarioInstrumentoServicio) {
        this.usuarioInstrumentoServicio = usuarioInstrumentoServicio;
    }

    @GetMapping
    public List<UsuarioInstrumento> obtenerTodos() {
        return usuarioInstrumentoServicio.obtenerTodos();
    }

    @GetMapping("/{idUsuario}/{idInstrumento}/{idVoz}")
    public ResponseEntity<UsuarioInstrumento> obtenerPorId(
            @PathVariable Integer idUsuario, 
            @PathVariable Integer idInstrumento, 
            @PathVariable Integer idVoz) {
            
        Optional<UsuarioInstrumento> asignacion = usuarioInstrumentoServicio.obtenerPorId(idUsuario, idInstrumento, idVoz);
        if (asignacion.isPresent()) {
            return ResponseEntity.ok(asignacion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioInstrumento> guardar(@RequestBody UsuarioInstrumento usuarioInstrumento) {
        return ResponseEntity.ok(usuarioInstrumentoServicio.guardar(usuarioInstrumento));
    }

    @DeleteMapping("/{idUsuario}/{idInstrumento}/{idVoz}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer idUsuario, 
            @PathVariable Integer idInstrumento, 
            @PathVariable Integer idVoz) {
            
        usuarioInstrumentoServicio.eliminar(idUsuario, idInstrumento, idVoz);
        return ResponseEntity.ok().build();
    }
}