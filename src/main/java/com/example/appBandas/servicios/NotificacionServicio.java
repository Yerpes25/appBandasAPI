package com.example.appBandas.servicios;

import com.example.appBandas.modelos.Notificacion;
import com.example.appBandas.modelos.Usuario;
import com.example.appBandas.repositorios.NotificacionRepository;
import com.example.appBandas.repositorios.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificacionServicio {

    private final NotificacionRepository notificacionRepository;
    private final UsuarioRepository usuarioRepository;

    public NotificacionServicio(NotificacionRepository notificacionRepository, UsuarioRepository usuarioRepository) {
        this.notificacionRepository = notificacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Notificacion> obtenerNotificacionesUsuario(Integer idUsuario) {
        return notificacionRepository.findByIdUsuarioAndActivoTrueOrderByFechaEnvioDesc(idUsuario);
    }

    public void enviarIndividual(Integer idUsuario, String titulo, String mensaje) {
        Notificacion n = new Notificacion();
        n.setIdUsuario(idUsuario);
        n.setTitulo(titulo);
        n.setMensaje(mensaje);
        notificacionRepository.save(n);
    }

    public void enviarATodos(String titulo, String mensaje) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario u : usuarios) {
            if (u.getActivo()) {
                enviarIndividual(u.getIdUsuario(), titulo, mensaje);
            }
        }
    }

    public void enviarADuenos(String titulo, String mensaje) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario u : usuarios) {
            if (u.getActivo() && u.getRolApp() != null && u.getRolApp().toLowerCase().contains("due")) {
                enviarIndividual(u.getIdUsuario(), titulo, mensaje);
            }
        }
    }

    public void marcarComoLeida(Integer idNotificacion) {
        notificacionRepository.findById(idNotificacion).ifPresent(n -> {
            n.setLeida(true);
            notificacionRepository.save(n);
        });
    }

    public void desactivarNotificacion(Integer idNotificacion) {
        notificacionRepository.findById(idNotificacion).ifPresent(n -> {
            n.setActivo(false);
            notificacionRepository.save(n);
        });
    }
}