package com.example.appBandas.controladores;

import com.example.appBandas.modelos.CredencialesLogin;
import com.example.appBandas.modelos.Notificacion;
import com.example.appBandas.modelos.Usuario;
import com.example.appBandas.servicios.NotificacionServicio;
import com.example.appBandas.servicios.UsuarioServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionControlador {

    private final NotificacionServicio notificacionServicio;
    private final UsuarioServicio usuarioServicio;

    public NotificacionControlador(NotificacionServicio notificacionServicio, UsuarioServicio usuarioServicio) {
		super();
		this.notificacionServicio = notificacionServicio;
		this.usuarioServicio = usuarioServicio;
	}

	@GetMapping("/usuario/{idUsuario}")
    public List<Notificacion> obtenerPorUsuario(@PathVariable Integer idUsuario) {
        return notificacionServicio.obtenerNotificacionesUsuario(idUsuario);
    }

    @PostMapping("/enviar")
    public ResponseEntity<Void> enviar(@RequestBody Map<String, String> payload) {
        String tipo = payload.get("tipo"); // "GLOBAL", "DUENOS", "INDIVIDUAL"
        String titulo = payload.get("titulo");
        String mensaje = payload.get("mensaje");

        if (tipo.equals("GLOBAL")) {
            notificacionServicio.enviarATodos(titulo, mensaje);
        } else if (tipo.equals("DUENOS")) {
            notificacionServicio.enviarADuenos(titulo, mensaje);
        } else if (tipo.equals("INDIVIDUAL")) {
            Integer idDestino = Integer.parseInt(payload.get("idUsuario"));
            notificacionServicio.enviarIndividual(idDestino, titulo, mensaje);
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/leer/{id}")
    public ResponseEntity<Void> marcarLeida(@PathVariable Integer id) {
        notificacionServicio.marcarComoLeida(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Integer id) {
        notificacionServicio.desactivarNotificacion(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody CredencialesLogin credenciales) {
        Optional<Usuario> usuarioOpt = usuarioServicio.obtenerUsuarioPorEmail(credenciales.getCorreo());
        
        if (usuarioOpt.isEmpty()) return ResponseEntity.status(401).build();
        
        Usuario usuario = usuarioOpt.get();
        if (usuario.getPassword().equals(credenciales.getClave())) {
            
            // NOTIFICACIÓN POR DEFECTO: Aviso de seguridad
            notificacionServicio.enviarIndividual(
                usuario.getIdUsuario(), 
                "Nuevo inicio de sesión", 
                "Se ha detectado un acceso a tu cuenta hoy a las " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
            );

            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}