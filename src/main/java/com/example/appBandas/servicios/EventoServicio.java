package com.example.appBandas.servicios;

import org.springframework.stereotype.Service;

import com.example.appBandas.modelos.Banda;
import com.example.appBandas.modelos.Evento;
import com.example.appBandas.modelos.Usuario;
import com.example.appBandas.repositorios.BandaRepository;
import com.example.appBandas.repositorios.EventoRepository;
import com.example.appBandas.repositorios.UsuarioRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar los eventos (ensayos, actuaciones, etc.).
 */
@Service
public class EventoServicio {

	private final EventoRepository eventoRepository;
	private final BandaRepository bandaRepository;
	private final UsuarioRepository usuarioRepository;
	private final NotificacionServicio notificacionServicio;

	public EventoServicio(EventoRepository eventoRepository, BandaRepository bandaRepository,
			UsuarioRepository usuarioRepository, NotificacionServicio notificacionServicio) {
		super();
		this.eventoRepository = eventoRepository;
		this.bandaRepository = bandaRepository;
		this.usuarioRepository = usuarioRepository;
		this.notificacionServicio = notificacionServicio;
	}

	public List<Evento> obtenerTodosLosEventos() {
		return eventoRepository.findAll();
	}

	public Optional<Evento> obtenerEventoPorId(Integer id) {
		return eventoRepository.findById(id);
	}

	public Evento guardarEvento(Evento evento) {
		return eventoRepository.save(evento);
	}

	public void eliminarEvento(Integer id) {
		eventoRepository.deleteById(id);
	}

	public long ensayosHoyPorBanda() {
		return eventoRepository.contarEnsayosDeBandas();
	}

	/**
	 * Devuelve una lista de eventos que pertenecen exclusivamente a una banda.
	 */
	public List<Evento> obtenerEventosPorBanda(Integer idBanda) {
		return eventoRepository.findByBandaIdBanda(idBanda);
	}

	public Evento crearEventoParaBanda(Integer idBanda, java.util.Map<String, String> datos) throws Exception {
		Banda banda = bandaRepository.findById(idBanda).orElseThrow(() -> new Exception("La banda no existe."));

		Evento evento;
		boolean esNuevo = true;

		// COMPROBAMOS SI ES UNA EDICIÓN O UNO NUEVO
		if (datos.containsKey("idEvento") && datos.get("idEvento") != null) {
			Integer id = Integer.parseInt(datos.get("idEvento"));
			// Si tiene ID, lo buscamos en la base de datos para sobrescribirlo
			evento = eventoRepository.findById(id).orElse(new Evento());
			esNuevo = false;
		} else {
			// Si no tiene ID, es un evento totalmente nuevo
			evento = new Evento();
		}

		evento.setBanda(banda);
		evento.setTipo(datos.get("tipo"));
		evento.setTitulo(datos.get("titulo"));
		evento.setHoraFin(datos.get("horaFin"));
		evento.setDireccion(datos.get("ubicacion"));

		/*
		 * Comprobamos si nos llega la configuracion de requerir confirmacion. Lo
		 * pasamos a booleano y se lo asignamos al evento antes de guardarlo.
		 */
		if (datos.containsKey("requiereConf") && datos.get("requiereConf") != null) {
			evento.setRequiereConf(Boolean.parseBoolean(datos.get("requiereConf")));
		} else {
			// Por defecto, si no se especifica, lo ponemos a falso
			evento.setRequiereConf(false);
		}

		if (datos.get("fecha") != null && datos.get("horaInicio") != null) {
			String horaFormateada = datos.get("horaInicio").length() == 4 ? "0" + datos.get("horaInicio")
					: datos.get("horaInicio");
			java.time.LocalDateTime fHora = java.time.LocalDateTime.parse(datos.get("fecha") + "T" + horaFormateada);
			evento.setfHora(fHora);
		}
		
		Evento guardado = eventoRepository.save(evento);
		
		// --- NOTIFICACIÓN POR DEFECTO PARA NUEVOS EVENTOS ---
        if (esNuevo) {
            // Buscamos a todos los músicos que pertenezcan a esta banda
            List<Usuario> musicos = usuarioRepository.findByBanda_IdBanda(idBanda);
            
            String tituloMensaje = "📅 Nuevo " + (guardado.getTipo() != null ? guardado.getTipo() : "evento");
            String cuerpoMensaje = "Se ha publicado un nuevo evento: " + 
                                   (guardado.getTitulo() != null ? guardado.getTitulo() : "Revisar la agenda de la banda.");
            
            // Le mandamos la campanita a cada uno
            for (Usuario u : musicos) {
                notificacionServicio.enviarIndividual(u.getIdUsuario(), tituloMensaje, cuerpoMensaje);
            }
        }

		return guardado;
	}
}